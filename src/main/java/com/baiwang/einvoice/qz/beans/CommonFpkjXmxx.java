/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: CommonFpKj
  * @Description: TODO
  * @author wsdoing
  * @date 2016年2月29日 下午1:54:41
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fphxz",
    "xmmc",
    "ggxh",
    "dw",
    "xmsl",
    "xmdj",
    "xmje",
    "sl",
    "se"
})
public class CommonFpkjXmxx {

    @XmlElement(name = "FPHXZ")
    protected byte fphxz;
    @XmlElement(name = "XMMC", required = true)
    protected String xmmc;
    @XmlElement(name = "GGXH", required = true)
    protected String ggxh;
    @XmlElement(name = "DW", required = true)
    protected String dw;
    @XmlElement(name = "XMSL")
    protected byte xmsl;
    @XmlElement(name = "XMDJ")
    protected byte xmdj;
    @XmlElement(name = "XMJE")
    protected float xmje;
    @XmlElement(name = "SL")
    protected float sl;
    @XmlElement(name = "SE")
    protected float se;

    /**
     * Gets the value of the fphxz property.
     * 
     */
    public byte getFPHXZ() {
        return fphxz;
    }

    /**
     * Sets the value of the fphxz property.
     * 
     */
    public void setFPHXZ(byte value) {
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
     */
    public byte getXMSL() {
        return xmsl;
    }

    /**
     * Sets the value of the xmsl property.
     * 
     */
    public void setXMSL(byte value) {
        this.xmsl = value;
    }

    /**
     * Gets the value of the xmdj property.
     * 
     */
    public byte getXMDJ() {
        return xmdj;
    }

    /**
     * Sets the value of the xmdj property.
     * 
     */
    public void setXMDJ(byte value) {
        this.xmdj = value;
    }

    /**
     * Gets the value of the xmje property.
     * 
     */
    public float getXMJE() {
        return xmje;
    }

    /**
     * Sets the value of the xmje property.
     * 
     */
    public void setXMJE(float value) {
        this.xmje = value;
    }

    /**
     * Gets the value of the sl property.
     * 
     */
    public float getSL() {
        return sl;
    }

    /**
     * Sets the value of the sl property.
     * 
     */
    public void setSL(float value) {
        this.sl = value;
    }

    /**
     * Gets the value of the se property.
     * 
     */
    public float getSE() {
        return se;
    }

    /**
     * Sets the value of the se property.
     * 
     */
    public void setSE(float value) {
        this.se = value;
    }

}
