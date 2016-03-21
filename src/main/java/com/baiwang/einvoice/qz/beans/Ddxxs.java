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
  * @ClassName: Ddxxs
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月15日 上午10:22:25
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderDetail"
})
public class Ddxxs {

    @XmlElement(name = "DDXX")
    protected List<OrderDetail> orderDetail;
    @XmlAttribute(name = "LSH")
    protected String lsh;

    
    public List<OrderDetail> getOrderDetail() {
        if (orderDetail == null) {
        	orderDetail = new ArrayList<OrderDetail>();
        }
        return this.orderDetail;
    }


	public String getLsh() {
	
		return lsh;
	}


	public void setLsh(String lsh) {
	
		this.lsh = lsh;
	}

    
   
}
