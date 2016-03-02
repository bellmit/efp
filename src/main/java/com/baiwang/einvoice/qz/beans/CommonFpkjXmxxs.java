/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: CommonFpkjXmxxs
  * @Description: TODO
  * @author wsdoing
  * @date 2016年2月29日 下午1:56:48
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fpmx"
})
public class CommonFpkjXmxxs {

    @XmlElement(name = "COMMON_FPKJ_XMXX")
    protected List<Fpmx> fpmx;
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "size")
    protected Byte size;

    public List<Fpmx> getFpmx() {
        if (fpmx == null) {
        	fpmx = new ArrayList<Fpmx>();
        }
        return this.fpmx;
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

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setSize(Byte value) {
        this.size = value;
    }
}
