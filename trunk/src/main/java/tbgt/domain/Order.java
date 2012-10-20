package tbgt.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import tbgt.common.CustomJsonDateSerializer;
import tbgt.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

public class Order {
    private long id;
    private String receiver_state ;
    private String receiver_address;
    private String buyer_msg;
    private Express express;
    private List<SoldBaobei> soldBaobeis;
    private BigDecimal actualPrice;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date pay_time;
    private String status;
    private Timestamp modified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    public BigDecimal getActualPrice() {
        return actualPrice == null ? BigDecimal.ZERO : actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }


    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public List<SoldBaobei> getSoldBaobeis() {
        return soldBaobeis;
    }


    public void setSoldBaobeis(List<SoldBaobei> soldBaobeis) {
        this.soldBaobeis = soldBaobeis;
    }

    public void addSoldBaobei(SoldBaobei soldBaobei) {
        if (this.soldBaobeis == null) {
            this.soldBaobeis = new ArrayList<SoldBaobei>();
        }
        this.soldBaobeis.add(soldBaobei);
    }

    @JsonIgnore
    public BigDecimal getPurchasePrice() {
        BigDecimal totalPurchasePrice = BigDecimal.ZERO;
        for (SoldBaobei soldBaobei : soldBaobeis) {
            BigDecimal purchasePrice = soldBaobei.getPurchase_price();
            purchasePrice = purchasePrice != null ? purchasePrice : BigDecimal.ZERO;
            purchasePrice = purchasePrice.multiply(new BigDecimal(soldBaobei.getQuantity()));
            totalPurchasePrice = totalPurchasePrice.add(purchasePrice);
        }
        return totalPurchasePrice;
    }

    @JsonIgnore
    public BigDecimal getProfit() {
        return actualPrice.subtract(getPurchasePrice())
                .subtract(getExpress().getFee()).subtract(getExpress().getAgencyFee()).subtract(getExpress().getGiftFee());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiver_state() {
        return receiver_state;
    }

    public void setReceiver_state(String receiver_state) {
        this.receiver_state = receiver_state;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getBuyer_msg() {
        return buyer_msg;
    }

    public void setBuyer_msg(String buyer_msg) {
        this.buyer_msg = buyer_msg;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }
}
