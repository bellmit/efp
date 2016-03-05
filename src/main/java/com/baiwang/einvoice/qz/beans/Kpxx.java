package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"id",
	"fpqqlsh",
	"fpdm",
	"fphm",
	"zddh",
	"fddh",
    "fplx",
    "kplx",
    "xsfnsrsbh",
    "xsfmc",
    "xsfdz",
    "xsfdh",
    "xsfyhzh",
    "gmfnsrsbh",
    "gmfmc",
    "gmfdz",
    "gmfdh",
    "gmfyhzh",
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
	@XmlElement(name = "ID", required = true)
    private Long id;
	@XmlElement(name = "FPQQLSH", required = true)
    private String fpqqlsh;
	@XmlElement(name = "FPDM", required = true)
    private String fpdm;
	@XmlElement(name = "FPHM", required = true)
    private String fphm;
	@XmlElement(name = "FPZT", required = true)
    private String fpzt;
	@XmlElement(name = "RESULT_CODE", required = true)
    private String resultcode;
	@XmlElement(name = "RESULT_MSG", required = true)
    private String resultmsg;
	@XmlElement(name = "ZDDH", required = true)
    private String zddh;
	@XmlElement(name = "FDDH", required = true)
    private String fddh;
	@XmlElement(name = "FPLX", required = true)
    private String fplx;
	@XmlElement(name = "KPLX", required = true)
    private Boolean kplx;
	@XmlElement(name = "XSFNSRSBH", required = true)
    private String xsfnsrsbh;
	@XmlElement(name = "XSFMC", required = true)
    private String xsfmc;
	@XmlElement(name = "XSFDZ", required = true)
    private String xsfdz;
	@XmlElement(name = "XSFDH", required = true)
    private String xsfdh;
	@XmlElement(name = "XSFYHZH", required = true)
    private String xsfyhzh;
	@XmlElement(name = "GMFNSRSBH", required = true)
    private String gmfnsrsbh;
	@XmlElement(name = "GMFMC", required = true)
    private String gmfmc;
	@XmlElement(name = "GMFDZ", required = true)
    private String gmfdz;
	@XmlElement(name = "GMFDH", required = true)
    private String gmfdh;
	@XmlElement(name = "GMFYHZH", required = true)
    private String gmfyhzh;
	@XmlElement(name = "KPR", required = true)
    private String kpr;
	@XmlElement(name = "SKR", required = true)
    private String skr;
	@XmlElement(name = "FHR", required = true)
    private String fhr;
	@XmlElement(name = "YFPDM", required = true)
    private String yfpdm;
	@XmlElement(name = "YFPHM", required = true)
    private String yfphm;
	@XmlElement(name = "JSHJ", required = true)
    private Float jshj;
	@XmlElement(name = "HJJE", required = true)
    private Float hjje;
	@XmlElement(name = "HJSE", required = true)
    private Float hjse;
	@XmlElement(name = "BZ", required = true)
    private String bz;
	@XmlElement(name = "KPRQ", required = true)
    private String kprq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh == null ? null : fpqqlsh.trim();
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm == null ? null : fpdm.trim();
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm == null ? null : fphm.trim();
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt == null ? null : fpzt.trim();
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode == null ? null : resultcode.trim();
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg == null ? null : resultmsg.trim();
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx == null ? null : fplx.trim();
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

    public String getXsfdz() {
        return xsfdz;
    }

    public void setXsfdz(String xsfdz) {
        this.xsfdz = xsfdz == null ? null : xsfdz.trim();
    }

    public String getXsfdh() {
        return xsfdh;
    }

    public void setXsfdh(String xsfdh) {
        this.xsfdh = xsfdh == null ? null : xsfdh.trim();
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

    public String getGmfdz() {
        return gmfdz;
    }

    public void setGmfdz(String gmfdz) {
        this.gmfdz = gmfdz == null ? null : gmfdz.trim();
    }

    public String getGmfdh() {
        return gmfdh;
    }

    public void setGmfdh(String gmfdh) {
        this.gmfdh = gmfdh == null ? null : gmfdh.trim();
    }

    public String getGmfyhzh() {
        return gmfyhzh;
    }

    public void setGmfyhzh(String gmfyhzh) {
        this.gmfyhzh = gmfyhzh == null ? null : gmfyhzh.trim();
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

	public String getZddh() {
	
		return zddh;
	}

	public void setZddh(String zddh) {
	
		this.zddh = zddh;
	}

	public String getFddh() {
	
		return fddh;
	}

	public void setFddh(String fddh) {
	
		this.fddh = fddh;
	}
    
    
}