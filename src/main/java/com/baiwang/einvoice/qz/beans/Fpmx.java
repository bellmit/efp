/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: fpmx
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月2日 下午2:24:47
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
public class Fpmx{

	@XmlElement(name = "ID", required = true)
    private String id;
	@XmlElement(name = "FPQQLSH", required = true)
    private String fpqqlsh;
    @XmlElement(name = "FPHXZ", required = true)
    protected String fphxz;
    @XmlElement(name = "XMMC", required = true)
    protected String xmmc;
    @XmlElement(name = "GGXH", required = true)
    protected String ggxh;
    @XmlElement(name = "DW", required = true)
    protected String dw;
    @XmlElement(name = "XMSL", required = true)
    protected String xmsl;
    @XmlElement(name = "XMDJ", required = true)
    protected String xmdj;
    @XmlElement(name = "XMJE", required = true)
    protected String xmje;
    @XmlElement(name = "SL", required = true)
    protected String sl;
    @XmlElement(name = "SE", required = true)
    protected String se;
    @XmlElement(name = "HSBZ", required = true)
    protected String hsbz;

    /**
     * Gets the value of the fphxz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFPHXZ() {
        return fphxz;
    }

    /**
     * Sets the value of the fphxz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFPHXZ(String value) {
        this.fphxz = value;
    }

    /**
     * Gets the value of the xmmc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMMC() {
        return xmmc;
    }

    /**
     * Sets the value of the xmmc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMMC(String value) {
        this.xmmc = value;
    }

    /**
     * Gets the value of the ggxh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGGXH() {
        return ggxh;
    }

    /**
     * Sets the value of the ggxh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGGXH(String value) {
        this.ggxh = value;
    }

    /**
     * Gets the value of the dw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDW() {
        return dw;
    }

    /**
     * Sets the value of the dw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDW(String value) {
        this.dw = value;
    }

    /**
     * Gets the value of the xmsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMSL() {
        return xmsl;
    }

    /**
     * Sets the value of the xmsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMSL(String value) {
        this.xmsl = value;
    }

    /**
     * Gets the value of the xmdj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMDJ() {
        return xmdj;
    }

    /**
     * Sets the value of the xmdj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMDJ(String value) {
        this.xmdj = value;
    }

    /**
     * Gets the value of the xmje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMJE() {
        return xmje;
    }

    /**
     * Sets the value of the xmje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMJE(String value) {
        this.xmje = value;
    }

    /**
     * Gets the value of the sl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSL() {
        return sl;
    }

    /**
     * Sets the value of the sl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSL(String value) {
        this.sl = value;
    }

    /**
     * Gets the value of the se property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSE() {
        return se;
    }

    /**
     * Sets the value of the se property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSE(String value) {
        this.se = value;
    }

    /**
     * Gets the value of the hsbz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHSBZ() {
        return hsbz;
    }

    /**
     * Sets the value of the hsbz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHSBZ(String value) {
        this.hsbz = value;
    }

	public String getFpqqlsh() {
	
		return fpqqlsh;
	}

	public void setFpqqlsh(String fpqqlsh) {
	
		this.fpqqlsh = fpqqlsh;
	}

	public String getId() {
	
		return id;
	}

	public void setId(String id) {
	
		this.id = id;
	}

    
}
