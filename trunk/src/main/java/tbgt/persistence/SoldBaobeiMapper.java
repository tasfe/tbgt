package tbgt.persistence;

import tbgt.domain.SoldBaobei;

import java.util.List;

public interface SoldBaobeiMapper {

    public void saveSoldBaoBei(SoldBaobei soldBaobei);

    public void updateSoldBaoBei(SoldBaobei soldBaobei);

    public List<SoldBaobei> getSoldBaobeiByOrderId(String orderNo);

    public void deleteSoldBaobei(long id);

}
