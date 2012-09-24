package tbgt.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SoldBaobei {
    private int id;
    private int baobeiId;
    private int quantity;
    private String color;
    private String spec;


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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
