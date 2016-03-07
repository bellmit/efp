/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.Business;
import com.baiwang.einvoice.qz.beans.CustomOrder;
import com.baiwang.einvoice.qz.beans.Fpmx;
import com.baiwang.einvoice.qz.beans.Kpxx;
import com.baiwang.einvoice.qz.dao.CustomOrderMapper;
import com.baiwang.einvoice.qz.dao.FpmxMapper;
import com.baiwang.einvoice.qz.dao.KpxxMapper;
import com.baiwang.einvoice.qz.dao.OrderDetailMapper;
import com.baiwang.einvoice.qz.service.FpService;
import com.baiwang.einvoice.qz.utils.XmlUtil;

/**
  * @ClassName: FpServiceImpl
  * @Description: TODO
  * @author wsdoing
  * @date 2016年3月3日 上午9:25:47
  */
@Service("fpService")
public class FpServiceImpl implements FpService {

	private static final Log logger = LogFactory.getLog(FpServiceImpl.class);
	
	@Resource
	private KpxxMapper dao;
	@Resource
	private FpmxMapper fpmxDao;
	@Resource
	private CustomOrderMapper orderDao;
	@Resource
	private OrderDetailMapper orderDetailDao;
	
	/**
	  * <p>Title: saveXmlInfo</p>
	  * <p>Description: </p>
	  * @param business
	  * @see com.baiwang.einvoice.qz.service.FpService#saveXmlInfo(com.baiwang.einvoice.qz.beans.Business)
	  */
	@Override
	public void saveXmlInfo(Business business) {

		String fpqqlsh = XmlUtil.random();
		CustomOrder customOrder = business.getCustomOrder();
		//订单记录去重保存
		int count = orderDao.count(customOrder.getDdhm());
		if(count==0){
			orderDao.insertSelective(customOrder);
			logger.info("存入订单号为【"+customOrder.getDdhm()+"】的订单信息！");
		}else{
			orderDao.updateByPrimaryKeySelective(customOrder);
			logger.info("更新订单号为【"+customOrder.getDdhm()+"】的订单信息！");
		}
		
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		kpxx.setFpqqlsh(fpqqlsh);
		//kpxx.setDdhm(customOrder.getDdhm());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		kpxx.setKprq(sf.format(new Date()));
		
		try{
			dao.deleteByDdhm(customOrder.getDdhm());
			dao.insert(kpxx);
			
			List<Fpmx> list = business.getREQUESTCOMMONFPKJ().getCommonfpkjxmxxs().getFpmx();
			if(list.size()>0){
				for(Fpmx fpmx: list){
					fpmx.setFpqqlsh(fpqqlsh);
				}
				
				fpmxDao.insertFromList(list);
			}
		}catch(Exception e){
			logger.error(".....保存数据库失败");
			e.printStackTrace();
		}
	}

	/**
	  * <p>Title: saveInfo</p>
	  * <p>Description: </p>
	  * @param kpxx
	  * @param fpmxList
	  * @see com.baiwang.einvoice.qz.service.FpService#saveInfo(com.baiwang.einvoice.qz.beans.Kpxx, java.util.List)
	  */
	@Override
	public void saveInfo(CustomOrder customOrder, Kpxx kpxx, List<Fpmx> fpmxList) {
		
		int count = orderDao.count(customOrder.getDdhm());
		if(count==0){
			orderDao.insertSelective(customOrder);
			logger.info("存入订单号为【"+customOrder.getDdhm()+"】的订单信息！");
		}else{
			orderDao.updateByPrimaryKeySelective(customOrder);
			logger.info("更新订单号为【"+customOrder.getDdhm()+"】的订单信息！");
		}
		
		kpxx.setZddh(customOrder.getDdhm());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		kpxx.setKprq(sf.format(new Date()));
		
		dao.deleteByDdhm(customOrder.getDdhm());
		logger.info("开票信息重复，删除订单号为【"+customOrder.getDdhm()+"】的开票信息！");
		dao.insert(kpxx);
		logger.info("存入订单号为【"+customOrder.getDdhm()+"】的开票信息！");
		
		fpmxDao.deleteByFpqqlsh(kpxx.getFpqqlsh());
		logger.info("发票明细数据重复，删除流水号为【"+kpxx.getFpqqlsh()+"】的发票明细！");
		
		if(fpmxList.size()>0){
			for(Fpmx fpmx: fpmxList){
				fpmx.setFpqqlsh(kpxx.getFpqqlsh());
			}
			
			fpmxDao.insertFromList(fpmxList);
			logger.info("存入流水号为【"+kpxx.getFpqqlsh()+"】的发票明细！");
		}
		
	}

	/**
	  * <p>Title: getPlainList</p>
	  * <p>Description: </p>
	  * @return
	  * @see com.baiwang.einvoice.qz.service.FpService#getPlainList()
	  */
	@Override
	public List<Map<String, String>> getPlainList(HashMap<String, String> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getPlainList(param);
		
	}

	/**
	  * <p>Title: getKpxxByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @return
	  * @see com.baiwang.einvoice.qz.service.FpService#getKpxxByFpqqlsh(java.lang.String)
	  */
	@Override
	public Kpxx getKpxxByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		return dao.selectByFpqqlsh(fpqqlsh);
		
	}

	/**
	  * <p>Title: getFpmxByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @return
	  * @see com.baiwang.einvoice.qz.service.FpService#getFpmxByFpqqlsh(java.lang.String)
	  */
	@Override
	public List<Fpmx> getFpmxByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		return fpmxDao.selectByFpqqlsh(fpqqlsh);
		
	}

	/**
	  * <p>Title: updateFpztByFpqqlsh</p>
	  * <p>Description: </p>
	  * @param fpqqlsh
	  * @see com.baiwang.einvoice.qz.service.FpService#updateFpztByFpqqlsh(java.lang.String)
	  */
	@Override
	public void updateFpztByFpqqlsh(String fpqqlsh) {
		
		// TODO Auto-generated method stub
		dao.updateFpztByFpqqlsh(fpqqlsh);
	}

	/**
	  * <p>Title: getSpecialList</p>
	  * <p>Description: </p>
	  * @param param
	  * @return
	  * @see com.baiwang.einvoice.qz.service.FpService#getSpecialList(java.util.HashMap)
	  */
	@Override
	public List<Map<String, String>> getSpecialList(HashMap<String, String> param) {
		
		// TODO Auto-generated method stub
		return orderDetailDao.getSpecialList(param);
		
	}

	/**
	  * <p>Title: getXml</p>
	  * <p>Description: </p>
	  * @param kpxx
	  * @param fpmxList
	  * @return
	  * @see com.baiwang.einvoice.qz.service.FpService#getXml(com.baiwang.einvoice.qz.beans.Kpxx, java.util.List)
	  */
	@Override
	public String getXml(Kpxx kpxx, List<Fpmx> fpmxList) {
		
		String xml = "";
		
		if(kpxx != null){
			try {
			if("004".equals(kpxx.getFplx())){
				xml = XmlUtil.toSpecialInvoice(kpxx, fpmxList);
				logger.info("开具增值税纸质专用发票，报文为："+ xml);
			}else if("007".equals(kpxx.getFplx())){
				xml = XmlUtil.toPlainInvoice(kpxx, fpmxList);
				logger.info("开具增值税纸质普通发票，报文为："+ xml);
			}else if("026".equals(kpxx.getFplx())){
				xml = XmlUtil.toEInvoice(kpxx, fpmxList);
				logger.info("开具增值税电子普通发票，报文为："+ xml);
			}
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
				logger.error("报文封装失败：" + e.getMessage());
			}
		}
		
		return xml;
	}

}
