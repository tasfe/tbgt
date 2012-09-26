package tbgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tbgt.domain.Baobei;
import tbgt.domain.Price;
import tbgt.persistence.BaobeiMapper;
import tbgt.persistence.PriceMapper;

import java.util.List;

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

    public void deleteBaobei(int id) {
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
