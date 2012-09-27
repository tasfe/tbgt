package tbgt.web.criteria;

import org.springframework.format.annotation.DateTimeFormat;
import tbgt.util.DateUtil;

import java.util.Date;

public class OrderCriteria {
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date fromDate;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date toDate;
    private String name;
    private String isSent;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSent() {
        return isSent;
    }

    public void setSent(String sent) {
        isSent = sent;
    }
}
