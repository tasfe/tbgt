package tbgt.service;

import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Baobei;
import tbgt.domain.Price;
import tbgt.persistence.BaobeiMapper;
import tbgt.persistence.PriceMapper;
import tbgt.web.paging.PaginationTO;
import tbgt.web.paging.PagingContextHolder;
import tbgt.web.paging.PagingEnabler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaoBeiServiceImpl implements BaoBeiService {


    private BaobeiMapper baobeiMapper;
    private PriceMapper priceMapper;

    public void setBaobeiMapper(BaobeiMapper baobeiMapper) {
        this.baobeiMapper = baobeiMapper;
    }

    public void setPriceMapper(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public List<Baobei> getAllBaobei() {
        return baobeiMapper.getAllBaobei();
    }

    public PaginationTO getBaobeiWithPaging(){
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("sSearch", PagingContextHolder.get().getsSearch());
        paraMap.put("sSortColumn_0", PagingContextHolder.get().getsSortColumn_0());
        paraMap.put("sSortDir_0", PagingContextHolder.get().getsSortDir_0());
        List<Baobei> baobeis = baobeiMapper.getBaobeiWithPaging(paraMap);
        return  PagingEnabler.enablePaging(baobeis);
    }

    public void deleteBaobei(int id) {
        Price price = priceMapper.getPrice(id);
        priceMapper.deletePrice(price.getId());
        baobeiMapper.deleteBaobei(id);
    }

    public Baobei getBaobeiById(int id) {
        return baobeiMapper.getBaobeiById(id);
    }

    @Transactional
    public void insertBaobei(Baobei baobei) {
        baobeiMapper.insertBaobei(baobei);
        Price price = baobei.getPrice();
        price.setBaobeiId(baobei.getId());
        priceMapper.insertPrice(price);

    }

    @Transactional
    public void updateBaobei(Baobei baobei) {
        baobeiMapper.updateBaobei(baobei);
        Price price = baobei.getPrice();
        priceMapper.updatePrice(price);
    }
}
