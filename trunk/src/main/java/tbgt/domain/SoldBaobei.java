package tbgt.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SoldBaobei {
    private long id;
    private long tid;
    private String sku_id;
    private long bbid;
    private long quantity = 1;
    private String sku_properties_name;
    private String title;
    private String weight;
    private BigDecimal purchase_price;
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public long getBbid() {
        return bbid;
    }

    public void setBbid(long bbid) {
        this.bbid = bbid;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getSku_properties_name() {
        return sku_properties_name;
    }

    public void setSku_properties_name(String sku_properties_name) {
        this.sku_properties_name = sku_properties_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
}
