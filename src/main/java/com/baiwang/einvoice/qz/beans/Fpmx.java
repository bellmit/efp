package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: Fpmx
  * @Description: 封装发票明细的实体类
  * @author zhaowei
  * @date 2016年3月14日 下午4:19:29
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"id",
	"fpqqlsh",
    "fphxz",
    "xmmc",
    "ggxh",
    "dw",
    "xmsl",
    "xmdj",
    "xmje",
    "sl",
    "se",
    "hsbz"
})
public class Fpmx {
	
	@XmlElement(name = "ID", required = true)
    private Long id;
	@XmlElement(name = "FPQQLSH", required = true)
    private String fpqqlsh;
	@XmlElement(name = "FPHXZ", required = true)
    private Boolean fphxz;
	@XmlElement(name = "XMMC", required = true)
    private String xmmc;
	@XmlElement(name = "GGXH", required = true)
    private String ggxh;
	@XmlElement(name = "DW", required = true)
    private String dw;
	@XmlElement(name = "XMSL", required = true)
    private Float xmsl;
	@XmlElement(name = "XMDJ", required = true)
    private Float xmdj;
	@XmlElement(name = "XMJE", required = true)
    private Float xmje;
	@XmlElement(name = "SL", required = true)
    private Float sl;
	@XmlElement(name = "SE", required = true)
    private Float se;
	@XmlElement(name = "HSBZ", required = true)
    private Boolean hsbz;

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

    public Boolean getFphxz() {
        return fphxz;
    }

    public void setFphxz(Boolean fphxz) {
        this.fphxz = fphxz;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc == null ? null : xmmc.trim();
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh == null ? null : ggxh.trim();
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw == null ? null : dw.trim();
    }

    public Float getXmsl() {
        return xmsl;
    }

    public void setXmsl(Float xmsl) {
        this.xmsl = xmsl;
    }

    public Float getXmdj() {
        return xmdj;
    }

    public void setXmdj(Float xmdj) {
        this.xmdj = xmdj;
    }

    public Float getXmje() {
        return xmje;
    }

    public void setXmje(Float xmje) {
        this.xmje = xmje;
    }

    public Float getSl() {
        return sl;
    }

    public void setSl(Float sl) {
        this.sl = sl;
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se;
    }

    public Boolean getHsbz() {
        return hsbz;
    }

    public void setHsbz(Boolean hsbz) {
        this.hsbz = hsbz;
    }
}