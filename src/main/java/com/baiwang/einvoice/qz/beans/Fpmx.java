package com.baiwang.einvoice.qz.beans;

public class Fpmx {
    private Long id;

    private String fpqqlsh;

    private Boolean fphxz;

    private String xmmc;

    private String ggxh;

    private String dw;

    private Float xmsl;

    private Float xmdj;

    private Float xmje;

    private Float sl;

    private Float se;

    private Boolean hsbz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh == null ? null : fpqqlsh.trim();
    }

    public Boolean getFphxz() {
        return fphxz;
    }

    public void setFphxz(Boolean fphxz) {
        this.fphxz = fphxz;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc == null ? null : xmmc.trim();
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh == null ? null : ggxh.trim();
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw == null ? null : dw.trim();
    }

    public Float getXmsl() {
        return xmsl;
    }

    public void setXmsl(Float xmsl) {
        this.xmsl = xmsl;
    }

    public Float getXmdj() {
        return xmdj;
    }

    public void setXmdj(Float xmdj) {
        this.xmdj = xmdj;
    }

    public Float getXmje() {
        return xmje;
    }

    public void setXmje(Float xmje) {
        this.xmje = xmje;
    }

    public Float getSl() {
        return sl;
    }

    public void setSl(Float sl) {
        this.sl = sl;
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se;
    }

    public Boolean getHsbz() {
        return hsbz;
    }

    public void setHsbz(Boolean hsbz) {
        this.hsbz = hsbz;
    }
}