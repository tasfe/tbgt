package tbgt.service;

import tbgt.domain.Baobei;
import tbgt.domain.BaobeiSku;
import tbgt.web.paging.PaginationTO;

import java.util.List;

public interface BaoBeiService {

    public PaginationTO getBaobei();

    public void deleteBaobei(long id);

    public Baobei getBaobeiById(long id);

    public void insertBaobei(Baobei baobei);

    public void updateBaobei(Baobei baobei);

    public void updateBaobeiCustomAttr(Baobei baobei);

    public BaobeiSku getSkuById(long sku_id);
}
