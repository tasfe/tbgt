package tbgt.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import tbgt.common.CustomJsonDateSerializer;

import java.util.Date;

public class Baobei {
    private int id;
	private String name;
	private String taobaoId;
	private String saleTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date incomingTime;
	public Price price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaleTitle() {
        return saleTitle;
    }

    public void setSaleTitle(String saleTitle) {
        this.saleTitle = saleTitle;
    }

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getIncomingTime() {
        return incomingTime;
    }

    public void setIncomingTime(Date incomingTime) {
        this.incomingTime = incomingTime;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getTaobaoLink() {
        return "http://item.taobao.com/item.htm?id="+taobaoId;
    }

    public String getTaobaoId() {
        return taobaoId;
    }

    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }
}
