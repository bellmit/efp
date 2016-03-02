/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: Kpxx
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月2日 下午2:31:12
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"fpqqlsh",
	"ddhm",
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

	@XmlElement(name = "DD_HM", required = true)
    private String ddhm;
    @XmlElement(name = "FPLX", required = true)
    protected String fplx;
    @XmlElement(name = "KPLX", required = true)
    protected String kplx;
    @XmlElement(name = "XSF_NSRSBH", required = true)
    protected String xsfnsrsbh;
    @XmlElement(name = "XSF_MC", required = true)
    protected String xsfmc;
    @XmlElement(name = "XSF_DZ", required = true)
    protected String xsfdz;
    @XmlElement(name = "XSF_DH", required = true)
    protected String xsfdh;
    @XmlElement(name = "XSF_YHZH", required = true)
    protected String xsfyhzh;
    @XmlElement(name = "GMF_NSRSBH", required = true)
    protected String gmfnsrsbh;
    @XmlElement(name = "GMF_MC", required = true)
    protected String gmfmc;
    @XmlElement(name = "GMF_DZ", required = true)
    protected String gmfdz;
    @XmlElement(name = "GMF_DH", required = true)
    protected String gmfdh;
    @XmlElement(name = "GMF_YHZH", required = true)
    protected String gmfyhzh;
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
    @XmlElement(name = "JSHJ", required = true)
    protected String jshj;
    @XmlElement(name = "HJJE", required = true)
    protected String hjje;
    @XmlElement(name = "HJSE", required = true)
    protected String hjse;
    @XmlElement(name = "BZ", required = true)
    protected String bz;
    @XmlElement(name = "kprq", required = true)
    private String kprq;

    @XmlElement(name = "FPQQLSH", required = true)
    private String fpqqlsh;
    
    
    
    public String getKprq() {
	
		return kprq;
	}


	public void setKprq(String kprq) {
	
		this.kprq = kprq;
	}


	public String getFpqqlsh() {
	
		return fpqqlsh;
	}


	public void setFpqqlsh(String fpqqlsh) {
	
		this.fpqqlsh = fpqqlsh;
	}


	public String getFPLX() {
        return fplx;
    }

    
    public void setFPLX(String value) {
        this.fplx = value;
    }

    
    public String getKPLX() {
        return kplx;
    }

    
    public void setKPLX(String value) {
        this.kplx = value;
    }

    
    public String getXSFNSRSBH() {
        return xsfnsrsbh;
    }

   
    public void setXSFNSRSBH(String value) {
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
     * Gets the value of the xsfdz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSFDZ() {
        return xsfdz;
    }

    /**
     * Sets the value of the xsfdz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSFDZ(String value) {
        this.xsfdz = value;
    }

    /**
     * Gets the value of the xsfdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSFDH() {
        return xsfdh;
    }

    /**
     * Sets the value of the xsfdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSFDH(String value) {
        this.xsfdh = value;
    }

    /**
     * Gets the value of the xsfyhzh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSFYHZH() {
        return xsfyhzh;
    }

    /**
     * Sets the value of the xsfyhzh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSFYHZH(String value) {
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
     * Gets the value of the gmfdz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFDZ() {
        return gmfdz;
    }

    /**
     * Sets the value of the gmfdz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFDZ(String value) {
        this.gmfdz = value;
    }

    /**
     * Gets the value of the gmfdh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFDH() {
        return gmfdh;
    }

    /**
     * Sets the value of the gmfdh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFDH(String value) {
        this.gmfdh = value;
    }

    /**
     * Gets the value of the gmfyhzh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMFYHZH() {
        return gmfyhzh;
    }

    /**
     * Sets the value of the gmfyhzh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMFYHZH(String value) {
        this.gmfyhzh = value;
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
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJSHJ() {
        return jshj;
    }

    /**
     * Sets the value of the jshj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJSHJ(String value) {
        this.jshj = value;
    }

    /**
     * Gets the value of the hjje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHJJE() {
        return hjje;
    }

    /**
     * Sets the value of the hjje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHJJE(String value) {
        this.hjje = value;
    }

    /**
     * Gets the value of the hjse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHJSE() {
        return hjse;
    }

    /**
     * Sets the value of the hjse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHJSE(String value) {
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


	public String getDdhm() {
	
		return ddhm;
	}


	public void setDdhm(String ddhm) {
	
		this.ddhm = ddhm;
	}

    
}
