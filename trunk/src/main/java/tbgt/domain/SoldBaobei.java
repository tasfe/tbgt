package tbgt.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SoldBaobei {
    private int id;
    private int baobeiId;
    private int expressId;
    private int quantity;
    private Date soldTime;
    private BigDecimal actualPrice;
    public Express express;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaobeiId() {
        return baobeiId;
    }

    public void setBaobeiId(int baobeiId) {
        this.baobeiId = baobeiId;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(Date soldTime) {
        this.soldTime = soldTime;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }
}
