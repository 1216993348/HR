package cn.xhy.shop.vo;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable{
    private Integer gid ;
    private String gtitle;
    private Date gpubdate;
    private Double gprice;
    private Integer gamount;
    private String gphoto;
    private Integer gbrow;
    private String gnote;
    private Integer gstatus;
    private Admin admin;
    private Item item ;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGtitle() {
        return gtitle;
    }

    public void setGtitle(String gtitle) {
        this.gtitle = gtitle;
    }

    public Date getGpubdate() {
        return gpubdate;
    }

    public void setGpubdate(Date gpubdate) {
        this.gpubdate = gpubdate;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Integer getGamount() {
        return gamount;
    }

    public void setGamount(Integer gamount) {
        this.gamount = gamount;
    }

    public String getGphoto() {
        return gphoto;
    }

    public void setGphoto(String gphoto) {
        this.gphoto = gphoto;
    }

    public Integer getGbrow() {
        return gbrow;
    }

    public void setGbrow(Integer gbrow) {
        this.gbrow = gbrow;
    }

    public String getGnote() {
        return gnote;
    }

    public void setGnote(String gnote) {
        this.gnote = gnote;
    }

    public Integer getGstatus() {
        return gstatus;
    }

    public void setGstatus(Integer gstatus) {
        this.gstatus = gstatus;
    }
}
