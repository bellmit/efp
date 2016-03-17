package com.baiwang.einvoice.qz.beans;

import java.util.Date;

public class User {
    private String id;

    private String czydm;

    private String czymc;

    private String yhlx;

    private String yhkl;

    private String qybz;

    private String cjrdm;

    private Date cjsj;

    private String kpddm;

    private String fwqbh;

    private String nsrsbh;

    private String zzjgdm;
    
    private String fplxdm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCzydm() {
        return czydm;
    }

    public void setCzydm(String czydm) {
        this.czydm = czydm == null ? null : czydm.trim();
    }

    public String getCzymc() {
        return czymc;
    }

    public void setCzymc(String czymc) {
        this.czymc = czymc == null ? null : czymc.trim();
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx == null ? null : yhlx.trim();
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl == null ? null : yhkl.trim();
    }

    public String getQybz() {
        return qybz;
    }

    public void setQybz(String qybz) {
        this.qybz = qybz == null ? null : qybz.trim();
    }

    public String getCjrdm() {
        return cjrdm;
    }

    public void setCjrdm(String cjrdm) {
        this.cjrdm = cjrdm == null ? null : cjrdm.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getKpddm() {
        return kpddm;
    }

    public void setKpddm(String kpddm) {
        this.kpddm = kpddm == null ? null : kpddm.trim();
    }

    public String getFwqbh() {
        return fwqbh;
    }

    public void setFwqbh(String fwqbh) {
        this.fwqbh = fwqbh == null ? null : fwqbh.trim();
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh == null ? null : nsrsbh.trim();
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm == null ? null : zzjgdm.trim();
    }

	public String getFplxdm() {
	
		return fplxdm;
	}

	public void setFplxdm(String fplxdm) {
	
		this.fplxdm = fplxdm;
	}
    
    
}