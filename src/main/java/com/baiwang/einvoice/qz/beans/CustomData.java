/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: CustomData
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月21日 下午5:15:17
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ddxxs"
})
public class CustomData {

    @XmlElement(name = "DDXXS", required = true)
    protected Ddxxs ddxxs;

	public Ddxxs getDdxxs() {
	
		return ddxxs;
	}

	public void setDdxxs(Ddxxs ddxxs) {
	
		this.ddxxs = ddxxs;
	}

    
}
