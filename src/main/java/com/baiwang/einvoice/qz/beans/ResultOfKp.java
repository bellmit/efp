package com.baiwang.einvoice.qz.beans;

import java.util.Date;

public class ResultOfKp {
    private Long id;

    private String ddhm;

    private String correlationid;

    private String fpqqlsh;

    private String code;

    private String msg;

    private Date operatetime;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdhm() {
        return ddhm;
    }

    public void setDdhm(String ddhm) {
        this.ddhm = ddhm == null ? null : ddhm.trim();
    }

    public String getCorrelationid() {
        return correlationid;
    }

    public void setCorrelationid(String correlationid) {
        this.correlationid = correlationid == null ? null : correlationid.trim();
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh == null ? null : fpqqlsh.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}