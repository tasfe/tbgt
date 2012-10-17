package tbgt.domain;

import org.springframework.format.annotation.DateTimeFormat;
import tbgt.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class Express {
    private int id;
    private long orderId;
    private String expressNo;
    private BigDecimal fee;
    private BigDecimal giftFee;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getFee() {
        return fee==null?BigDecimal.ZERO:fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }


    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public BigDecimal getGiftFee() {
        return giftFee;
    }

    public void setGiftFee(BigDecimal giftFee) {
        this.giftFee = giftFee;
    }
}
