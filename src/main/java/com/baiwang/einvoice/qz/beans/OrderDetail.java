package com.baiwang.einvoice.qz.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/***
  * @ClassName: OrderDetail
  * @Description: 订单信息实体类
  * @author zhaowei
  * @date 2016年3月14日 下午4:23:41
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"id",
	"fpqqlsh",
	"zddh",
	"fddh",
	"kpdq",
    "sqr",
    "hym",
    "hyid",
    "ddsj",
    "sqsj",
    "spzl",
    "sqrk",
    "shr",
    "shrdh",
    "jsdz",
    "yjsj",
    "fhr",
    "wlgs",
    "wldh",
    "tkzt",
    "fpzt"
    
})
public class OrderDetail {
	@XmlElement(name = "ID", required = true)
    private Long id;
	@XmlElement(name = "FPQQLSH", required = true)
	private String fpqqlsh;
	@XmlElement(name = "ZDDH", required = true)
    private String zddh;
	@XmlElement(name = "FDDH", required = true)
    private String fddh;
	@XmlElement(name = "KPDQ", required = true)
	private Byte kpdq;
	@XmlElement(name = "SQR", required = true)
    private String sqr;
	@XmlElement(name = "HYM", required = true)
    private String hym;
	@XmlElement(name = "HYID", required = true)
    private String hyid;
	@XmlElement(name = "DDSJ", required = true)
    private Date ddsj;
	@XmlElement(name = "SQSJ", required = true)
    private Date sqsj;
	@XmlElement(name = "SPZL", required = true)
    private String spzl;
	@XmlElement(name = "SQRK", required = true)
    private String sqrk;
	@XmlElement(name = "SHR", required = true)
    private String shr;
	@XmlElement(name = "SHRDH", required = true)
    private String shrdh;
	@XmlElement(name = "JSDZ", required = true)
    private String jsdz;
	@XmlElement(name = "YJSJ", required = true)
    private Date yjsj;
	@XmlElement(name = "FHR", required = true)
    private String fhr;
	@XmlElement(name = "WLGS", required = true)
    private String wlgs;
	@XmlElement(name = "WLDH", required = true)
    private String wldh;
	@XmlElement(name = "TKZT", required = true)
    private String tkzt;
	@XmlElement(name = "FPZT", required = true)
    private String fpzt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSqrk() {
        return sqrk;
    }

    public void setSqrk(String sqrk) {
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

	public String getFpqqlsh() {
	
		return fpqqlsh;
	}

	public void setFpqqlsh(String fpqqlsh) {
	
		this.fpqqlsh = fpqqlsh;
	}

	public Byte getKpdq() {
	
		return kpdq;
	}

	public void setKpdq(Byte kpdq) {
	
		this.kpdq = kpdq;
	}

    
}