package com.baiwang.einvoice.qz.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"ddhm",
    "ddssdq",
    "ddywlx",
    "ddsj"
})
public class CustomOrder {
	@XmlElement(name = "DD_HM", required = true)
    private String ddhm;
    @XmlElement(name = "DD_SS_DQ", required = true)
    private String ddssdq;
    @XmlElement(name = "DD_YW_LX", required = true)
    private String ddywlx;
    @XmlElement(name = "DD_SJ", required = true)
    private String ddsj;

    public String getDdhm() {
        return ddhm;
    }

    public void setDdhm(String ddhm) {
        this.ddhm = ddhm == null ? null : ddhm.trim();
    }

    public String getDdssdq() {
        return ddssdq;
    }

    public void setDdssdq(String ddssdq) {
        this.ddssdq = ddssdq == null ? null : ddssdq.trim();
    }

    public String getDdywlx() {
        return ddywlx;
    }

    public void setDdywlx(String ddywlx) {
        this.ddywlx = ddywlx == null ? null : ddywlx.trim();
    }

    public String getDdsj() {
        return ddsj;
    }

    public void setDdsj(String ddsj) {
        this.ddsj = ddsj == null ? null : ddsj.trim();
    }
}