package cn.xhy.shop.vo;

import java.io.Serializable;

public class Item implements Serializable{
    Integer iid ;
    String ititle ;
    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getItitle() {
        return ititle;
    }

    public void setItitle(String ititle) {
        this.ititle = ititle;
    }
}
