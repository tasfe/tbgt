package tbgt.service;

import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Baobei;
import tbgt.domain.BaobeiSku;
import tbgt.persistence.BaobeiMapper;
import tbgt.persistence.BaobeiSkuMapper;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.web.paging.PagingEnabler;
import tbgt.util.TaobaoClientUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Sku;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.request.ItemGetRequest;

public class BaoBeiServiceImpl implements BaoBeiService {


    private BaobeiMapper baobeiMapper;
    private BaobeiSkuMapper baobeiSkuMapper;

    public void setBaobeiMapper(BaobeiMapper baobeiMapper) {
        this.baobeiMapper = baobeiMapper;
    }

    public void setBaobeiSkuMapper(BaobeiSkuMapper baobeiSkuMapper) {
        this.baobeiSkuMapper = baobeiSkuMapper;
    }

    public PaginationTO getBaobei(){
        Map<String, String> paraMap = new HashMap<String, String>();
        PaginationTO paginationTO = PagingContextHolder.get();
        if (paginationTO != null) {
            paraMap.put("sSearch", paginationTO.getsSearch());
            paraMap.put("sSortColumn_0", paginationTO.getsSortColumn_0());
            paraMap.put("sSortDir_0", paginationTO.getsSortDir_0());
        }
        List<Baobei> baobeis = baobeiMapper.getBaobei(paraMap);
        return  PagingEnabler.enablePaging(baobeis);
    }

    public void deleteBaobei(long id) {
        List<BaobeiSku> baobeiSkus = baobeiSkuMapper.getSkuByBbid(id);
        for (BaobeiSku sku : baobeiSkus) {
            baobeiSkuMapper.deleteSku(sku.getSku_id());
        }
        baobeiMapper.deleteBaobei(id);
    }

    public Baobei getBaobeiById(long id) {
        return baobeiMapper.getBaobeiById(id);
    }

    @Transactional
    public void insertBaobei(Baobei baobei) {
        baobeiMapper.insertBaobei(baobei);
        for (BaobeiSku sku : baobei.getSkus()) {
            baobeiSkuMapper.insertSku(sku);
        }
    }

    @Transactional
    public void updateBaobei(Baobei baobei) {
        baobeiMapper.updateBaobei(baobei);
        for (BaobeiSku sku : baobei.getSkus()) {
            if (baobeiSkuMapper.getSkuByBbid(baobei.getId()) != null) {
                baobeiSkuMapper.updateSku(sku);
            } else {
                baobeiSkuMapper.insertSku(sku);
            }
        }
    }

    @Transactional
    public void updateBaobeiCustomAttr(Baobei baobei) {
        for (BaobeiSku sku : baobei.getSkus()) {
            baobeiSkuMapper.updateBaobeiCustomAttr(sku);
        }
    }

    public BaobeiSku getSkuById(long sku_id){
         return baobeiSkuMapper.getSkuById(sku_id);
    }

    @Override
    @Transactional
    public void refreshBaobei(String top_session) throws ApiException {
       TaobaoClient client = TaobaoClientUtil.getTaobaoClient();
        ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
        req.setFields("num_iid");
        ItemsOnsaleGetResponse res = client.execute(req, top_session);
        List<Item> items = res.getItems();
        for (Item item : items) {
            long id = item.getNumIid();
            ItemGetRequest itemGetRequest = new ItemGetRequest();
            itemGetRequest.setNumIid(id);
            itemGetRequest.setFields("num_iid,title,pic_url,detail_url,property_alias,sku,list_time");
            ItemGetResponse itemGetResponse = client.execute(itemGetRequest, top_session);
            item = itemGetResponse.getItem();
            Baobei baobei = new Baobei();
            baobei.setId(id);
            baobei.setDetail_url(item.getDetailUrl());
            baobei.setList_time(item.getListTime());
            baobei.setPic_url(item.getPicUrl());
            baobei.setProperty_alias(item.getPropertyAlias());
            baobei.setTitle(item.getTitle());
            List<Sku> skus = item.getSkus();
            if(skus!=null){
                for(Sku sku : skus){
                    BaobeiSku baobeiSku = new BaobeiSku();
                    baobeiSku.setBbid(id);
                    baobeiSku.setPrice(new BigDecimal(sku.getPrice()));
                    baobeiSku.setProperties_name(sku.getPropertiesName());
                    baobeiSku.setQuantity(sku.getQuantity());
                    baobeiSku.setSku_id(sku.getSkuId());
                    baobei.addSku(baobeiSku);
                }
            }
            if(getBaobeiById(baobei.getId())!=null){
               updateBaobei(baobei);
            }else{
               insertBaobei(baobei);
            }

        }
    }
}
