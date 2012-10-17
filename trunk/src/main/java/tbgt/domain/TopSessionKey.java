package tbgt.domain;

import java.sql.Timestamp;

public class TopSessionKey {
    private String callback_url;
    private Timestamp req_date;
    private String sessionkey;
    private int id;

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public Timestamp getReq_date() {
        return req_date;
    }

    public void setReq_date(Timestamp req_date) {
        this.req_date = req_date;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
