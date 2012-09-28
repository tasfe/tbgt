package tbgt.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import tbgt.common.CustomJsonDateSerializer;
import tbgt.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String orderNo;
    private String detail;
    private String address;
    private String contactPerson;
    private String phone;
    private Express express;
    private List<SoldBaobei> soldBaobeis;
    private BigDecimal actualPrice;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date soldTime;
    private BigDecimal agencyFee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    public BigDecimal getActualPrice() {
        return actualPrice==null?BigDecimal.ZERO:actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(Date soldTime) {
        this.soldTime = soldTime;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAgencyFee() {
        return agencyFee==null?BigDecimal.ZERO:agencyFee;
    }

    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    public BigDecimal getPurchasePrice(){
        BigDecimal totalPurchasePrice = BigDecimal.ZERO;
        for(SoldBaobei soldBaobei : soldBaobeis){
            BigDecimal purchasePrice = soldBaobei.getPurchasePrice();
            purchasePrice = purchasePrice != null ? purchasePrice : BigDecimal.ZERO;
            purchasePrice =  purchasePrice.multiply(new BigDecimal(soldBaobei.getQuantity()));
            totalPurchasePrice = totalPurchasePrice.add(purchasePrice);
        }
        return totalPurchasePrice;
    }

    public BigDecimal getProfit(){
        return actualPrice.subtract(getPurchasePrice())
                .subtract(getExpress().getFee()).subtract(getAgencyFee());
    }
}
