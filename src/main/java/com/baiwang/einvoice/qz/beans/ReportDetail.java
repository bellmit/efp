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
	
	private Integer number;
	//订单号
	private String ddh;
	//申请人
	private String sqr;
	//会员名
	private String hym;
	//会员ID
	private String hyid;
	//订单时间
	private Date ddsj;
	//申请时间
	private Date sqsj;
	//发票抬头
	private String fptt;
	//发票类型
	private String fplx;
	//发票种类
	private String fpzl;
	//申请入口
	private String sqrk;
	//金额
	private Integer je;
	//收货人
	private String shr;
	//收货人电话
	private String shrdh;
	//寄送地址
	private String jsdz;
	//邮寄时间
	private Date yjsj;
	//发票号码
	private String fphm;
	//发货人
	private String fhr;
	//物流公司 
	private String wlgs;
	//物流单号
	private String wldh;
	//退款状态
	private String tkzt;
	//发票状态
	private String fpzt;
	//订单发票是否开具打印
	private String ddzt;
	
	
	public String getDdzt() {
	
		return ddzt;
	}
	public void setDdzt(String ddzt) {
	
		this.ddzt = ddzt;
	}
	public Integer getNumber() {
	
		return number;
	}
	public void setNumber(Integer number) {
	
		this.number = number;
	}
	public String getDdh() {
	
		return ddh;
	}
	public void setDdh(String ddh) {
	
		this.ddh = ddh;
	}
	public String getSqr() {
	
		return sqr;
	}
	public void setSqr(String sqr) {
	
		this.sqr = sqr;
	}
	public String getHym() {
	
		return hym;
	}
	public void setHym(String hym) {
	
		this.hym = hym;
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
	public String getFptt() {
	
		return fptt;
	}
	public void setFptt(String fptt) {
	
		this.fptt = fptt;
	}
	public String getFplx() {
	
		return fplx;
	}
	public void setFplx(String fplx) {
	
		this.fplx = fplx;
	}
	public String getFpzl() {
	
		return fpzl;
	}
	public void setFpzl(String fpzl) {
	
		this.fpzl = fpzl;
	}
	public String getSqrk() {
	
		return sqrk;
	}
	public void setSqrk(String sqrk) {
	
		this.sqrk = sqrk;
	}
	public Integer getJe() {
	
		return je;
	}
	public void setJe(Integer je) {
	
		this.je = je;
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
	public String getTkzt() {
	
		return tkzt;
	}
	public void setTkzt(String tkzt) {
	
		this.tkzt = tkzt;
	}
	public String getFpzt() {
	
		return fpzt;
	}
	public void setFpzt(String fpzt) {
	
		this.fpzt = fpzt;
	}

}
