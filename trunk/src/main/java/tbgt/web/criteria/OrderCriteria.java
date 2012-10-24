package tbgt.web.criteria;

import org.springframework.format.annotation.DateTimeFormat;
import tbgt.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class OrderCriteria {
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date fromDate;
    @DateTimeFormat(pattern = DateUtil.DATE_FORMAT)
    private Date toDate;
    private String name;
    private List<String> statuses = null;

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

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

    public void addStatus(String status) {
        if(statuses == null) statuses = new ArrayList<String>();
        this.statuses.add(status);
    }
}
