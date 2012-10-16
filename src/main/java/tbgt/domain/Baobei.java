package tbgt.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import tbgt.common.CustomJsonDateSerializer;
import tbgt.util.DateUtil;

import java.util.*;

public class Baobei {
    private long id;
    private String detail_url;
    private String pic_url;
    private String title;
    private String property_alias;
    private Map<String, String> propAliasMap = new HashMap<String, String>();;
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

    public List<BaobeiSku> getFormatSkus() {
        if (!skus.isEmpty()) {
            for (BaobeiSku sku : skus) {
                String[] props = sku.getProperties_name().split(";");
                StringBuffer newProps = new StringBuffer();
                for (String prop : props) {
                    int vName_index = prop.lastIndexOf(":");
                    String pidvidpname = prop.substring(0, vName_index-1);
                    int pname_index = pidvidpname.lastIndexOf(":");
                    String pidvid = prop.substring(0, pname_index);
                    String pname = prop.substring(pname_index+1, vName_index);
                    String vname = prop.substring(vName_index+1, prop.length());
                    String propAlias = getPropAliasMap().get(pidvid);
                    if (propAlias != null) {
                        vname = propAlias;
                    }
                    newProps.append(pname).append(" : ").append(vname).append(", ");
                }
                sku.setProperties_name(newProps.substring(0, newProps.length() - 2));

            }
        }
        return skus;
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

    private Map<String, String> getPropAliasMap() {
        if (propAliasMap.isEmpty()) {
            String[] propAlias = property_alias.split(";");
            for (String propAlia : propAlias) {
                int index = propAlia.lastIndexOf(":");
                propAliasMap.put(propAlia.substring(0, index), propAlia.substring(index + 1, propAlia.length()));
            }
        }
        return propAliasMap;
    }
}
