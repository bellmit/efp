package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fpqqlsh",
    "xtbs",
    "jhkey",
    "kplx",
    "xsfnsrsbh",
    "xsfmc",
    "xsfdzdh",
    "xsfyhzh",
    "gmfnsrsbh",
    "gmfmc",
    "gmfdzdh",
    "gmfyhzh",
    "gmfyx",
    "gmfsjh",
    "kpr",
    "skr",
    "fhr",
    "yfpdm",
    "yfphm",
    "jshj",
    "hjje",
    "hjse",
    "bz",
    "kprq"
})
public class Kpxx {
	@XmlElement(name = "FPQQLSH", required = true)
    private String fpqqlsh;
	@XmlElement(name = "XTBS", required = true)
    private String xtbs;
	@XmlElement(name = "JHKEY")
    private String jhkey;
	@XmlElement(name = "KPLX")
    private Boolean kplx;
	@XmlElement(name = "XSF_NSRSBH")
    private String xsfnsrsbh;
	@XmlElement(name = "XSF_MC", required = true)
    private String xsfmc;
	@XmlElement(name = "XSF_DZDH")
    private String xsfdzdh;
	@XmlElement(name = "XSF_YHZH")
    private String xsfyhzh;
	@XmlElement(name = "GMF_NSRSBH", required = true)
    private String gmfnsrsbh;
	@XmlElement(name = "GMF_MC", required = true)
    private String gmfmc;
	@XmlElement(name = "GMF_DZDH", required = true)
    private String gmfdzdh;
	@XmlElement(name = "GMF_YHZH")
    private String gmfyhzh;
	@XmlElement(name = "GMF_YX", required = true)
    private String gmfyx;
	@XmlElement(name = "GMF_SJH")
    private String gmfsjh;
	@XmlElement(name = "KPR", required = true)
    private String kpr;
	@XmlElement(name = "SKR", required = true)
    private String skr;
	@XmlElement(name = "FHR", required = true)
    private String fhr;
	@XmlElement(name = "YFP_DM", required = true)
    private String yfpdm;
	@XmlElement(name = "YFP_HM", required = true)
    private String yfphm;
	@XmlElement(name = "JSHJ")
    private Float jshj;
	@XmlElement(name = "HJJE")
    private Float hjje;
	@XmlElement(name = "HJSE")
    private Float hjse;
	@XmlElement(name = "BZ", required = true)
    private String bz;
	@XmlElement(name = "KPRQ", required = true)
    private String kprq;

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getXtbs() {
        return xtbs;
    }

    public void setXtbs(String xtbs) {
        this.xtbs = xtbs == null ? null : xtbs.trim();
    }

    public String getJhkey() {
        return jhkey;
    }

    public void setJhkey(String jhkey) {
        this.jhkey = jhkey == null ? null : jhkey.trim();
    }

    public Boolean getKplx() {
        return kplx;
    }

    public void setKplx(Boolean kplx) {
        this.kplx = kplx;
    }

    public String getXsfnsrsbh() {
        return xsfnsrsbh;
    }

    public void setXsfnsrsbh(String xsfnsrsbh) {
        this.xsfnsrsbh = xsfnsrsbh == null ? null : xsfnsrsbh.trim();
    }

    public String getXsfmc() {
        return xsfmc;
    }

    public void setXsfmc(String xsfmc) {
        this.xsfmc = xsfmc == null ? null : xsfmc.trim();
    }

    public String getXsfdzdh() {
        return xsfdzdh;
    }

    public void setXsfdzdh(String xsfdzdh) {
        this.xsfdzdh = xsfdzdh == null ? null : xsfdzdh.trim();
    }

    public String getXsfyhzh() {
        return xsfyhzh;
    }

    public void setXsfyhzh(String xsfyhzh) {
        this.xsfyhzh = xsfyhzh == null ? null : xsfyhzh.trim();
    }

    public String getGmfnsrsbh() {
        return gmfnsrsbh;
    }

    public void setGmfnsrsbh(String gmfnsrsbh) {
        this.gmfnsrsbh = gmfnsrsbh == null ? null : gmfnsrsbh.trim();
    }

    public String getGmfmc() {
        return gmfmc;
    }

    public void setGmfmc(String gmfmc) {
        this.gmfmc = gmfmc == null ? null : gmfmc.trim();
    }

    public String getGmfdzdh() {
        return gmfdzdh;
    }

    public void setGmfdzdh(String gmfdzdh) {
        this.gmfdzdh = gmfdzdh == null ? null : gmfdzdh.trim();
    }

    public String getGmfyhzh() {
        return gmfyhzh;
    }

    public void setGmfyhzh(String gmfyhzh) {
        this.gmfyhzh = gmfyhzh == null ? null : gmfyhzh.trim();
    }

    public String getGmfyx() {
        return gmfyx;
    }

    public void setGmfyx(String gmfyx) {
        this.gmfyx = gmfyx == null ? null : gmfyx.trim();
    }

    public String getGmfsjh() {
        return gmfsjh;
    }

    public void setGmfsjh(String gmfsjh) {
        this.gmfsjh = gmfsjh == null ? null : gmfsjh.trim();
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr == null ? null : kpr.trim();
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr == null ? null : skr.trim();
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr == null ? null : fhr.trim();
    }

    public String getYfpdm() {
        return yfpdm;
    }

    public void setYfpdm(String yfpdm) {
        this.yfpdm = yfpdm == null ? null : yfpdm.trim();
    }

    public String getYfphm() {
        return yfphm;
    }

    public void setYfphm(String yfphm) {
        this.yfphm = yfphm == null ? null : yfphm.trim();
    }

    public Float getJshj() {
        return jshj;
    }

    public void setJshj(Float jshj) {
        this.jshj = jshj;
    }

    public Float getHjje() {
        return hjje;
    }

    public void setHjje(Float hjje) {
        this.hjje = hjje;
    }

    public Float getHjse() {
        return hjse;
    }

    public void setHjse(Float hjse) {
        this.hjse = hjse;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq == null ? null : kprq.trim();
    }
}