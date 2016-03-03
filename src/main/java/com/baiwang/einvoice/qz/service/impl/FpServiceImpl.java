/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		}else{
			orderDao.updateByPrimaryKeySelective(customOrder);
		}
		
		Kpxx kpxx = business.getREQUESTCOMMONFPKJ().getKpxx();
		kpxx.setFpqqlsh(fpqqlsh);
		kpxx.setDdhm(customOrder.getDdhm());
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

}
