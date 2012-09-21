package tbgt.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Express {
    private int id;
    private BigDecimal fee;
    private long orderNo;
    private Date sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
