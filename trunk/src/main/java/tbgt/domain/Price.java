package tbgt.domain;

import java.math.BigDecimal;

public class Price {
    private int id;
    private int baobeiId;
	private BigDecimal purchasePrice;
	private BigDecimal recommendedPrice;
	private BigDecimal salePrice;

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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(BigDecimal recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
