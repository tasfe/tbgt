package tbgt.persistence;

import tbgt.domain.SoldBaobei;

import java.util.List;

public interface SoldBaobeiMapper {

    public void saveSoldBaoBei(SoldBaobei soldBaobei);

    public List<SoldBaobei> getSoldBaobeiByOrderNo(String orderNo);

    public void deleteSoldBaobei(int id);

    public void updateSoldBaobei(SoldBaobei soldBaobei);
}
