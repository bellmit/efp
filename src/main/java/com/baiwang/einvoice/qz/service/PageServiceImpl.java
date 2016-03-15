/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
  * @ClassName: BaseServiceImpl
  * @Description: 分页基础服务类
  * @author zhaowei
  * @date 2015年8月12日 上午9:22:46
  */
@Service("pageService")
public class PageServiceImpl implements Serializable {

	private static final long serialVersionUID = 1293567786956029903L;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	protected SqlSessionFactoryBean sqlSessionFactory;
	
	public List<?> getPageList(Class<?> mapperClass, String sqlId,
	            Object sqlParameter, int pageIndex, int pageSize) {
	        SqlSession session = null;
	        try {
	            SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
	            session = SqlSessionUtils.getSqlSession(sessionFactory);
	            /*if (pageIndex <= 0) {
	                pageIndex = 0;
	            }*/
	            if (pageSize <= 0) {
	                pageSize = 10;
	            }
	            PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
	            return session.selectList(mapperClass.getName() + "." + sqlId,
	                    sqlParameter, pageBounds);
	        }catch(Exception e){
	        	logger.error("分页查询出错"+e.getMessage());
	        }
	        finally {
	        	if(null != session){
	        	   session.close();
		           logger.info("关闭数据库连接");
	        	}
	        }
			return null;

	    }

}
