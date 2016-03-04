/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiwang.einvoice.qz.beans.ReportDetail;
import com.baiwang.einvoice.qz.dao.ReportDetailMapper;
import com.baiwang.einvoice.qz.service.ReportDetailService;

/**
  * @ClassName: ReportDetailServiceImpl
  * @Description: TODO
  * @author Administrator
  * @date 2016年3月3日 下午8:25:17
  */
@Service
public class ReportDetailServiceImpl implements ReportDetailService {
	@Resource
	private ReportDetailMapper reportDao;

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param ddh
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public int deleteByPrimaryKey(String ddh) {
		
		// TODO Auto-generated method stub
		return reportDao.deleteByPrimaryKey(ddh);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param record
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public int insert(ReportDetail record) {
		
		// TODO Auto-generated method stub
		return reportDao.insert(record);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param record
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public int insertSelective(ReportDetail record) {
		
		// TODO Auto-generated method stub
		return reportDao.insertSelective(record);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param ddh
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public ReportDetail selectByPrimaryKey(String ddh) {
		
		// TODO Auto-generated method stub
		return reportDao.selectByPrimaryKey(ddh);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param record
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public int updateByPrimaryKeySelective(ReportDetail record) {
		
		// TODO Auto-generated method stub
		return reportDao.updateByPrimaryKeySelective(record);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param record
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public int updateByPrimaryKey(ReportDetail record) {
		
		// TODO Auto-generated method stub
		return reportDao.updateByPrimaryKey(record);
		
	}

	/**
	  * @author Administrator
	  * @Description: TODO
	  * @param @param condition
	  * @param @return  
	  * @throws
	  * @date 2016年3月3日 下午8:25:31
	  */
	@Override
	public List<ReportDetail> selectByCondition(Map<String, Object> condition) {
		
		return reportDao.selectByCondition(condition);
		
	}

}
