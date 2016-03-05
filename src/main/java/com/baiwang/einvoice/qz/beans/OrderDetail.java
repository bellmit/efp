package com.baiwang.einvoice.qz.beans;

import java.util.Date;

public class OrderDetail {
    private Long id;

    private String ddh;

    private String sqr;

    private String hym;

    private String hyid;

    private Date ddsj;

    private Date sqsj;

    private String spzl;

    private Date sqrk;

    private String shr;

    private String shrdh;

    private String jsdz;

    private Date yjsj;

    private String fhr;

    private String wlgs;

    private String wldh;

    private String tkzt;

    private String fpzt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh == null ? null : ddh.trim();
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr == null ? null : sqr.trim();
    }

    public String getHym() {
        return hym;
    }

    public void setHym(String hym) {
        this.hym = hym == null ? null : hym.trim();
    }

    public String getHyid() {
        return hyid;
    }

    public void setHyid(String hyid) {
        this.hyid = hyid == null ? null : hyid.trim();
    }

    public Date getDdsj() {
        return ddsj;
    }

    public void setDdsj(Date ddsj) {
        this.ddsj = ddsj;
    }

    public Date getSqsj() {
        return sqsj;
    }

    public void setSqsj(Date sqsj) {
        this.sqsj = sqsj;
    }

    public String getSpzl() {
        return spzl;
    }

    public void setSpzl(String spzl) {
        this.spzl = spzl == null ? null : spzl.trim();
    }

    public Date getSqrk() {
        return sqrk;
    }

    public void setSqrk(Date sqrk) {
        this.sqrk = sqrk;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr == null ? null : shr.trim();
    }

    public String getShrdh() {
        return shrdh;
    }

    public void setShrdh(String shrdh) {
        this.shrdh = shrdh == null ? null : shrdh.trim();
    }

    public String getJsdz() {
        return jsdz;
    }

    public void setJsdz(String jsdz) {
        this.jsdz = jsdz == null ? null : jsdz.trim();
    }

    public Date getYjsj() {
        return yjsj;
    }

    public void setYjsj(Date yjsj) {
        this.yjsj = yjsj;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr == null ? null : fhr.trim();
    }

    public String getWlgs() {
        return wlgs;
    }

    public void setWlgs(String wlgs) {
        this.wlgs = wlgs == null ? null : wlgs.trim();
    }

    public String getWldh() {
        return wldh;
    }

    public void setWldh(String wldh) {
        this.wldh = wldh == null ? null : wldh.trim();
    }

    public String getTkzt() {
        return tkzt;
    }

    public void setTkzt(String tkzt) {
        this.tkzt = tkzt == null ? null : tkzt.trim();
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt == null ? null : fpzt.trim();
    }
}