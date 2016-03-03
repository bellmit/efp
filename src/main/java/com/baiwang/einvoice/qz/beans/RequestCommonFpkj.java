/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
  * @ClassName: RequestCommonFpkj
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月2日 下午2:32:30
  */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "kpxx",
    "commonfpkjxmxxs"
})
public class RequestCommonFpkj {

	 @XmlElement(name = "COMMON_FPKJ_FPT", required = true)
     protected Kpxx kpxx;
     @XmlElement(name = "COMMON_FPKJ_XMXXS", required = true)
     protected Commonfpkjxmxxs commonfpkjxmxxs;

     
     public Kpxx getKpxx() {
         return kpxx;
     }

    
     public void setKpxx(Kpxx value) {
         this.kpxx = value;
     }

     
     public Commonfpkjxmxxs getCommonfpkjxmxxs() {
         return commonfpkjxmxxs;
     }

     
     public void setCommonfpkjxmxxs(Commonfpkjxmxxs value) {
         this.commonfpkjxmxxs = value;
     }
}
