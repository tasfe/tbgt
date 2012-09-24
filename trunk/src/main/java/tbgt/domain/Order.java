package tbgt.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderNo;
    private String detail;
    private String address;
    private String contactPerson;
    private String phone;
    private Express express;
    private List<SoldBaobei> soldBaobeis;
    private BigDecimal actualPrice;
    private Date soldTime;
    private Date deliverTime;

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
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Date getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(Date soldTime) {
        this.soldTime = soldTime;
    }

    public List<SoldBaobei> getSoldBaobeis() {
        return soldBaobeis;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
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

    public SoldBaobei getItem(int i) {
        return soldBaobeis.get(i);
    }
}
