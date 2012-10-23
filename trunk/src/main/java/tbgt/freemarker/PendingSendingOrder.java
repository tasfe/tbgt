package tbgt.freemarker;

import java.util.ArrayList;
import java.util.List;

public class PendingSendingOrder {
    private String name;
    private String address;
    private String sku_properties;
    private String no;
    private String num;
    private String memo;
    private String buyer_message;
    private String detail;
    private List<String> details = new ArrayList<String>();

    public String getSku_properties() {
        return sku_properties;
    }

    public void setSku_properties(String sku_properties) {
        this.sku_properties = sku_properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public void addDetail(String detail) {
        this.details.add(detail);
    }
}
