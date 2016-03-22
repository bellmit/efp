/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.util.Date;

/**
  * @ClassName: ReportDetail
  * @Description: 订单明细
  * @author Administrator
  * @date 2016年3月2日 下午2:37:59
  */
public class ReportDetail {
	
	//发票请求流水号
	private String fpqqlsh;
	//订单号
	private String ddh;
	//会员ID
	private String hyid;
	//地区
	private String fpdq;
	//订单时间
	private Date ddsj;
	//付款时间
	private Date fksj;
	//申请时间
	private Date sqsj;
	//发票抬头（***有限公司）
	private String fptt;
	//项目名称（服务费、咨询费等）
	private String xmmc;
	//备注发票
	private String bzfp;
	//发票类型
	private String fplx;
	//申请入口
	private String sqrk;
	//购方纳税人识别号
	private String gmfnsrsbh;
	//购方地址
	private String gmfdz;
	//购方电话
	private String gmfdh;
	//购方银行账号
	private String gmfyhzh;
	//发票金额
	private Float hjje;
	//收货人
	private String shr;
	//收货人电话
	private String shrdh;
	//寄送地址
	private String jsdz;
	//发票号码
	private String fphm;
	//发货人
	private String fhr;
	//物流公司 
	private String wlgs;
	//物流单号
	private String wldh;
	//邮寄时间
	private Date yjsj;
	//地址电话
	private String gfdzdh;
	
	public void setGfdzdh(String gfdzdh) {
	
		this.gfdzdh = gfdzdh;
	}
	public String getGfdzdh() {
	
		return gmfdz+gmfdh;
	}
	public String getBzfp() {
	
		return bzfp;
	}
	public void setBzfp(String bzfp) {
	
		this.bzfp = bzfp;
	}
	public String getFpqqlsh() {
		
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
	
		this.fpqqlsh = fpqqlsh;
	}
	public String getDdh() {
	
		return ddh;
	}
	public void setDdh(String ddh) {
	
		this.ddh = ddh;
	}
	public String getHyid() {
	
		return hyid;
	}
	public void setHyid(String hyid) {
	
		this.hyid = hyid;
	}
	public Date getDdsj() {
	
		return ddsj;
	}
	public void setDdsj(Date ddsj) {
	
		this.ddsj = ddsj;
	}
	public Date getSqsj() {
	
		return sqsj;
	}
	public void setSqsj(Date sqsj) {
	
		this.sqsj = sqsj;
	}
	public String getFpdq() {
	
		return fpdq;
	}
	public void setFpdq(String fpdq) {
	
		this.fpdq = fpdq;
	}
	public Date getFksj() {
	
		return fksj;
	}
	public void setFksj(Date fksj) {
	
		this.fksj = fksj;
	}
	public String getFptt() {
	
		return fptt;
	}
	public void setFptt(String fptt) {
	
		this.fptt = fptt;
	}
	public String getXmmc() {
	
		return xmmc;
	}
	public void setXmmc(String xmmc) {
	
		this.xmmc = xmmc;
	}
	public String getGmfnsrsbh() {
	
		return gmfnsrsbh;
	}
	public void setGmfnsrsbh(String gmfnsrsbh) {
	
		this.gmfnsrsbh = gmfnsrsbh;
	}
	public String getGmfdz() {
	
		return gmfdz;
	}
	public void setGmfdz(String gmfdz) {
	
		this.gmfdz = gmfdz;
	}
	public String getGmfdh() {
	
		return gmfdh;
	}
	public void setGmfdh(String gmfdh) {
	
		this.gmfdh = gmfdh;
	}
	public String getGmfyhzh() {
	
		return gmfyhzh;
	}
	public void setGmfyhzh(String gmfyhzh) {
	
		this.gmfyhzh = gmfyhzh;
	}
	public String getFplx() {
	
		return fplx;
	}
	public void setFplx(String fplx) {
	
		this.fplx = fplx;
	}
	public String getSqrk() {
	
		return sqrk;
	}
	public void setSqrk(String sqrk) {
	
		this.sqrk = sqrk;
	}
	public Float getHjje() {
	
		return hjje;
	}
	public void setHjje(Float hjje) {
	
		this.hjje = hjje;
	}
	public String getShr() {
	
		return shr;
	}
	public void setShr(String shr) {
	
		this.shr = shr;
	}
	public String getShrdh() {
	
		return shrdh;
	}
	public void setShrdh(String shrdh) {
	
		this.shrdh = shrdh;
	}
	public String getJsdz() {
	
		return jsdz;
	}
	public void setJsdz(String jsdz) {
	
		this.jsdz = jsdz;
	}
	public Date getYjsj() {
	
		return yjsj;
	}
	public void setYjsj(Date yjsj) {
	
		this.yjsj = yjsj;
	}
	public String getFphm() {
	
		return fphm;
	}
	public void setFphm(String fphm) {
	
		this.fphm = fphm;
	}
	public String getFhr() {
	
		return fhr;
	}
	public void setFhr(String fhr) {
	
		this.fhr = fhr;
	}
	public String getWlgs() {
	
		return wlgs;
	}
	public void setWlgs(String wlgs) {
	
		this.wlgs = wlgs;
	}
	public String getWldh() {
	
		return wldh;
	}
	public void setWldh(String wldh) {
	
		this.wldh = wldh;
	}

}
