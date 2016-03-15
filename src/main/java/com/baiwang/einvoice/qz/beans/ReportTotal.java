/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.util.Date;

/**
  * @ClassName: ReportTotal
  * @Description: 发票统计
  * @author Administrator
  * @date 2016年3月2日 下午2:28:56
  */
public class ReportTotal {
	
	private String fpqqlsh;//发票请求流水号
	private String fpdq;//发票地区
	private String fpkh;//发票客户
	private Date kprq;//开票日期
	private String fpzl;//发票种类
	private String fpdm;//发票代码
	private String fphm;//发票号码
	private String ttlx;//抬头类型
	private String gfmc;//购方名称
	private String fpnr;//发票内容
	private String bz;//备注
	private String bszt;//报送状态
	private String fpzt;//发票状态
	private Float hjje;//合计金额
	private Float hjse;//合计税额
	private Float jshj;//价税合计
	private String fpjd;//发票进度（开票、打印、作废）
	
	
	public String getFpjd() {
	
		return fpjd;
	}
	public void setFpjd(String fpjd) {
	
		this.fpjd = fpjd;
	}
	public String getFpqqlsh() {
		
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
	
		this.fpqqlsh = fpqqlsh;
	}
	public String getFpdq() {
	
		return fpdq;
	}
	public void setFpdq(String fpdq) {
	
		this.fpdq = fpdq;
	}
	public String getFpkh() {
	
		return fpkh;
	}
	public void setFpkh(String fpkh) {
	
		this.fpkh = fpkh;
	}
	public Date getKprq() {
	
		return kprq;
	}
	public void setKprq(Date kprq) {
	
		this.kprq = kprq;
	}
	public String getFpzl() {
	
		return fpzl;
	}
	public void setFpzl(String fpzl) {
	
		this.fpzl = fpzl;
	}
	public String getFpdm() {
	
		return fpdm;
	}
	public void setFpdm(String fpdm) {
	
		this.fpdm = fpdm;
	}
	public String getFphm() {
	
		return fphm;
	}
	public void setFphm(String fphm) {
	
		this.fphm = fphm;
	}
	public String getTtlx() {
	
		return ttlx;
	}
	public void setTtlx(String ttlx) {
	
		this.ttlx = ttlx;
	}
	public String getGfmc() {
	
		return gfmc;
	}
	public void setGfmc(String gfmc) {
	
		this.gfmc = gfmc;
	}
	public String getFpnr() {
	
		return fpnr;
	}
	public void setFpnr(String fpnr) {
	
		this.fpnr = fpnr;
	}
	public String getBz() {
	
		return bz;
	}
	public void setBz(String bz) {
	
		this.bz = bz;
	}
	public String getBszt() {
	
		return bszt;
	}
	public void setBszt(String bszt) {
	
		this.bszt = bszt;
	}
	public String getFpzt() {
	
		if(null != this.hjje && this.hjje>0){
			this.fpzt = "正数";
		}else if(null != this.hjje && this.hjje<0){
			this.fpzt = "负数";
		}
		if(null != this.fpzt && "-2".equals(this.fpjd)){
			this.fpzt+="作废";
		}
		return this.fpzt;
	}
	public void setFpzt(String fpzt) {
	
		this.fpzt = fpzt;
	}
	public Float getHjje() {
	
		return hjje;
	}
	public void setHjje(Float hjje) {
	
		this.hjje = hjje;
	}
	public Float getHjse() {
	
		return hjse;
	}
	public void setHjse(Float hjse) {
	
		this.hjse = hjse;
	}
	public Float getJshj() {
	
		return jshj;
	}
	public void setJshj(Float jshj) {
	
		this.jshj = jshj;
	}
	
	

}
