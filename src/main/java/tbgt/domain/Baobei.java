package tbgt.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import tbgt.common.CustomJsonDateSerializer;
import tbgt.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Baobei {
    private long id;
	private String detail_url;
	private String pic_url;
	private String title;
	private String property_alias;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
	private Date list_time;
	public List<BaobeiSku> skus = new ArrayList<BaobeiSku>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProperty_alias() {
        return property_alias;
    }

    public void setProperty_alias(String property_alias) {
        this.property_alias = property_alias;
    }
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    public Date getList_time() {
        return list_time;
    }

    public void setList_time(Date list_time) {
        this.list_time = list_time;
    }

    public List<BaobeiSku> getSkus() {
        return skus;
    }

    public void setSkus(List<BaobeiSku> skus) {
        this.skus = skus;
    }

    public void addSku(BaobeiSku sku) {
        skus.add(sku);
    }
}
