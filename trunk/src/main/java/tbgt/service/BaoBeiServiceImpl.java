package tbgt.service;

import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Baobei;
import tbgt.domain.BaobeiSku;
import tbgt.persistence.BaobeiMapper;
import tbgt.persistence.BaobeiSkuMapper;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.web.paging.PagingEnabler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
