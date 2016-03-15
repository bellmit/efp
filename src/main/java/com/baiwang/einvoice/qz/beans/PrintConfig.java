package com.baiwang.einvoice.qz.beans;

/**
  * @ClassName: PrintConfig
  * @Description: 打印参数配置实体类
  * @author gaokemeng
  * @date 2016年3月14日 下午4:24:08
 */
public class PrintConfig {
    private Integer id;

    private String fplx;

    private String topside;

    private String leftside;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx == null ? null : fplx.trim();
    }

    public String getTopside() {
        return topside;
    }

    public void setTopside(String topside) {
        this.topside = topside == null ? null : topside.trim();
    }

    public String getLeftside() {
        return leftside;
    }

    public void setLeftside(String leftside) {
        this.leftside = leftside == null ? null : leftside.trim();
    }
}