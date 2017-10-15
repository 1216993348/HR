package cn.xhy.shop.vo;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable{
    String aid ;
    String apassword;
    Date lastdate ;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }
}
