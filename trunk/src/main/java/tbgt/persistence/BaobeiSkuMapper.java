package tbgt.persistence;

import tbgt.domain.BaobeiSku;

import java.util.List;

public interface BaobeiSkuMapper {

    public void insertSku(BaobeiSku baobeiSku);

    public void updateSku(BaobeiSku baobeiSku);

    public void updateBaobeiCustomAttr(BaobeiSku baobeiSku);

    public void deleteSku(long sku_id);

    public List<BaobeiSku> getSkuByBbid(long id);

    public BaobeiSku getSkuById(long sku_id);

}
