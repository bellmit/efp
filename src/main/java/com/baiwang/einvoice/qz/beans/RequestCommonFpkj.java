/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: aaa
  * @Description: TODO
  * @author wsdoing
  * @date 2016年2月29日 下午2:02:27
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "kpxx",
    "commonfpkjxmxxs"
})
public  class RequestCommonFpkj {

    @XmlElement(name = "COMMON_FPKJ_FPT", required = true)
    protected Kpxx kpxx;
    @XmlElement(name = "COMMON_FPKJ_XMXXS", required = true)
    protected CommonFpkjXmxxs commonfpkjxmxxs;
    @XmlAttribute(name = "class")
    protected String clazz;

    
    /*public CommonFpkjFpt getCOMMONFPKJFPT() {
        return commonfpkjfpt;
    }


    public void setCOMMONFPKJFPT(CommonFpkjFpt value) {
        this.commonfpkjfpt = value;
    }*/

    
    public CommonFpkjXmxxs getCOMMONFPKJXMXXS() {
        return commonfpkjxmxxs;
    }

   
    public Kpxx getKpxx() {
	
		return kpxx;
	}


	public void setKpxx(Kpxx kpxx) {
	
		this.kpxx = kpxx;
	}


	public void setCOMMONFPKJXMXXS(CommonFpkjXmxxs value) {
        this.commonfpkjxmxxs = value;
    }

    
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

    }
