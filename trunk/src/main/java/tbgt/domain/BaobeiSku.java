package tbgt.domain;

import java.math.BigDecimal;

public class BaobeiSku {
    private long sku_id;
    private long bbid;
    private String properties_name;
    private long quantity;
    private BigDecimal purchase_price = BigDecimal.ZERO;
    private BigDecimal price;
    private String weight="0";

    public long getSku_id() {
        return sku_id;
    }

    public void setSku_id(long sku_id) {
        this.sku_id = sku_id;
    }

    public long getBbid() {
        return bbid;
    }

    public void setBbid(long bbid) {
        this.bbid = bbid;
    }

    public String getProperties_name() {
        return properties_name;
    }

    public void setProperties_name(String properties_name) {
        this.properties_name = properties_name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(BigDecimal purchase_price) {
        this.purchase_price = purchase_price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
