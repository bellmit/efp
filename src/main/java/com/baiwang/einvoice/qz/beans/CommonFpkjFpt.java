/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: sss
  * @Description: TODO
  * @author wsdoing
  * @date 2016年2月29日 下午2:00:37
  */
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
    "expand",
    "kprq"
})
public class CommonFpkjFpt {

    @XmlElement(name = "FPQQLSH", required = true)
    protected BigInteger fpqqlsh;
    @XmlElement(name = "XTBS", required = true)
    protected String xtbs;
    @XmlElement(name = "JHKEY")
    protected int jhkey;
    @XmlElement(name = "KPLX")
    protected byte kplx;
    @XmlElement(name = "XSF_NSRSBH")
    protected long xsfnsrsbh;
    @XmlElement(name = "XSF_MC", required = true)
    protected String xsfmc;
    @XmlElement(name = "XSF_DZDH")
    protected long xsfdzdh;
    @XmlElement(name = "XSF_YHZH")
    protected long xsfyhzh;
    @XmlElement(name = "GMF_NSRSBH", required = true)
    protected String gmfnsrsbh;
    @XmlElement(name = "GMF_MC", required = true)
    protected String gmfmc;
    @XmlElement(name = "GMF_DZDH", required = true)
    protected String gmfdzdh;
    @XmlElement(name = "GMF_YHZH")
    protected long gmfyhzh;
    @XmlElement(name = "GMF_YX", required = true)
    protected String gmfyx;
    @XmlElement(name = "GMF_SJH")
    protected long gmfsjh;
    @XmlElement(name = "KPR", required = true)
    protected String kpr;
    @XmlElement(name = "SKR", required = true)
    protected String skr;
    @XmlElement(name = "FHR", required = true)
    protected String fhr;
    @XmlElement(name = "YFP_DM", required = true)
    protected String yfpdm;
    @XmlElement(name = "YFP_HM", required = true)
    protected String yfphm;
    @XmlElement(name = "JSHJ")
    protected float jshj;
    @XmlElement(name = "HJJE")
    protected float hjje;
    @XmlElement(name = "HJSE")
    protected float hjse;
    @XmlElement(name = "BZ", required = true)
    protected String bz;
    @XmlElement(required = true)
    protected Expand expand;
    @XmlElement(name = "KPRQ", required = true)
    protected String kprq;
    @XmlAttribute(name = "class")
    protected String clazz;

    /**
     * Gets the value of the fpqqlsh property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFPQQLSH() {
        return fpqqlsh;
    }

    /**
     * Sets the value of the fpqqlsh property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFPQQLSH(BigInteger value) {
        this.fpqqlsh = value;
    }

    /**
     * Gets the value of the xtbs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXTBS() {
        return xtbs;
    }

    /**
     * Sets the value of the xtbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXTBS(String value) {
        this.xtbs = value;
    }

    /**
     * Gets the value of the jhkey property.
     * 
     */
    public int getJHKEY() {
        return jhkey;
    }

    /**
     * Sets the value of the jhkey property.
     * 
     */
    public void setJHKEY(int value) {
        this.jhkey = value;
    }

    /**
     * Gets the value of the kplx property.
     * 
     */
    public byte getKPLX() {
        return kplx;
    }

    /**
     * Sets the value of the kplx property.
     * 
     */
    public void setKPLX(byte value) {
        this.kplx = value;
    }

    /**
     * Gets the value of the xsfnsrsbh property.
     * 
     */
    public long getXSFNSRSBH() {
        return xsfnsrsbh;
    }

    /**
     * Sets the value of the xsfnsrsbh property.
     * 
     */
    public void setXSFNSRSBH(long value) {
        this.xsfnsrsbh = value;
    }

    /**
     * Gets the value of the xsfmc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSFMC() {
        return xsfmc;
    }

    /**
     * Sets the value of the xsfmc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSFMC(String value) {
        this.xsfmc = value;
    }

    /**
     * Gets the value of the xsfdzdh property.
     * 
     */
    public long getXSFDZDH() {
        return xsfdzdh;
    }

    /**
     * Sets the value of the xsfdzdh property.
     * 
     */
    public void setXSFDZDH(long value) {
        this.xsfdzdh = value;
    }

    /**
     * Gets the value of the xsfyhzh property.
     * 
     */
    public long getXSFYHZH() {
        return xsfyhzh;
    }

    /**
     * Sets the value of the xsfyhzh property.
     * 
     */
    public void setXSFYHZH(long value) {
        this.xsfyhzh = value;
    }

    /**
     * Gets the value of the gmfnsrsbh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFNSRSBH() {
        return gmfnsrsbh;
    }

    /**
     * Sets the value of the gmfnsrsbh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFNSRSBH(String value) {
        this.gmfnsrsbh = value;
    }

    /**
     * Gets the value of the gmfmc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFMC() {
        return gmfmc;
    }

    /**
     * Sets the value of the gmfmc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFMC(String value) {
        this.gmfmc = value;
    }

    /**
     * Gets the value of the gmfdzdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFDZDH() {
        return gmfdzdh;
    }

    /**
     * Sets the value of the gmfdzdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFDZDH(String value) {
        this.gmfdzdh = value;
    }

    /**
     * Gets the value of the gmfyhzh property.
     * 
     */
    public long getGMFYHZH() {
        return gmfyhzh;
    }

    /**
     * Sets the value of the gmfyhzh property.
     * 
     */
    public void setGMFYHZH(long value) {
        this.gmfyhzh = value;
    }

    /**
     * Gets the value of the gmfyx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFYX() {
        return gmfyx;
    }

    /**
     * Sets the value of the gmfyx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFYX(String value) {
        this.gmfyx = value;
    }

    /**
     * Gets the value of the gmfsjh property.
     * 
     */
    public long getGMFSJH() {
        return gmfsjh;
    }

    /**
     * Sets the value of the gmfsjh property.
     * 
     */
    public void setGMFSJH(long value) {
        this.gmfsjh = value;
    }

    /**
     * Gets the value of the kpr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKPR() {
        return kpr;
    }

    /**
     * Sets the value of the kpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKPR(String value) {
        this.kpr = value;
    }

    /**
     * Gets the value of the skr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSKR() {
        return skr;
    }

    /**
     * Sets the value of the skr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSKR(String value) {
        this.skr = value;
    }

    /**
     * Gets the value of the fhr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFHR() {
        return fhr;
    }

    /**
     * Sets the value of the fhr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFHR(String value) {
        this.fhr = value;
    }

    /**
     * Gets the value of the yfpdm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYFPDM() {
        return yfpdm;
    }

    /**
     * Sets the value of the yfpdm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYFPDM(String value) {
        this.yfpdm = value;
    }

    /**
     * Gets the value of the yfphm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYFPHM() {
        return yfphm;
    }

    /**
     * Sets the value of the yfphm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYFPHM(String value) {
        this.yfphm = value;
    }

    /**
     * Gets the value of the jshj property.
     * 
     */
    public float getJSHJ() {
        return jshj;
    }

    /**
     * Sets the value of the jshj property.
     * 
     */
    public void setJSHJ(float value) {
        this.jshj = value;
    }

    /**
     * Gets the value of the hjje property.
     * 
     */
    public float getHJJE() {
        return hjje;
    }

    /**
     * Sets the value of the hjje property.
     * 
     */
    public void setHJJE(float value) {
        this.hjje = value;
    }

    /**
     * Gets the value of the hjse property.
     * 
     */
    public float getHJSE() {
        return hjse;
    }

    /**
     * Sets the value of the hjse property.
     * 
     */
    public void setHJSE(float value) {
        this.hjse = value;
    }

    /**
     * Gets the value of the bz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBZ() {
        return bz;
    }

    /**
     * Sets the value of the bz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBZ(String value) {
        this.bz = value;
    }

    /**
     * Gets the value of the expand property.
     * 
     * @return
     *     possible object is
     *     {@link Business.REQUESTCOMMONFPKJ.COMMONFPKJFPT.Expand }
     *     
     */
    public Expand getExpand() {
        return expand;
    }

    /**
     * Sets the value of the expand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Business.REQUESTCOMMONFPKJ.COMMONFPKJFPT.Expand }
     *     
     */
    public void setExpand(Expand value) {
        this.expand = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

	public String getKprq() {
	
		return kprq;
	}

	public void setKprq(String kprq) {
	
		this.kprq = kprq;
	}

    
}
