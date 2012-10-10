package tbgt.web.criteria;

import java.math.BigDecimal;

public class ExpressCodeCriteria {
    private String province;
    private BigDecimal weight;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}
