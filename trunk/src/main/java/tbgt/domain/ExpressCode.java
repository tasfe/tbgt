package tbgt.domain;

public class ExpressCode {

    private String province;
    private String type;
    private int init_weight;
    private int init_price;
    private int increase_price;
    private int less1kg_price;
    private int days;
    private int calcPrice;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInit_weight() {
        return init_weight;
    }

    public void setInit_weight(int init_weight) {
        this.init_weight = init_weight;
    }

    public int getInit_price() {
        return init_price;
    }

    public void setInit_price(int init_price) {
        this.init_price = init_price;
    }

    public int getIncrease_price() {
        return increase_price;
    }

    public void setIncrease_price(int increase_price) {
        this.increase_price = increase_price;
    }

    public int getLess1kg_price() {
        return less1kg_price;
    }

    public void setLess1kg_price(int less1kg_price) {
        this.less1kg_price = less1kg_price;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCalcPrice() {
        return calcPrice;
    }

    public void setCalcPrice(int calcPrice) {
        this.calcPrice = calcPrice;
    }
}
