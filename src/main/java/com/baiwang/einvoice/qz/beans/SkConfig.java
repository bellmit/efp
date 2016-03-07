package com.baiwang.einvoice.qz.beans;

public class SkConfig {
    private Long id;

    private Integer kpdq;

    private String url;

    private String port;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKpdq() {
        return kpdq;
    }

    public void setKpdq(Integer kpdq) {
        this.kpdq = kpdq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }
}