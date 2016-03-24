/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.einvoice.qz.beans.EchartData;
import com.baiwang.einvoice.qz.beans.Series;
import com.baiwang.einvoice.qz.service.IFpService;

/**
  * @ClassName: SummaryController
  * @Description: 查询发票统计相关信息
  * @author zhaowei
  * @date 2016年3月17日 上午11:35:14
  */
@RequestMapping("")
@Controller
public class SummaryController {

	@Resource
	private IFpService fpService;
	
	@RequestMapping("/queryInvoiceNum")
	@ResponseBody
	public EchartData queryInvoiceNum(){
		
		List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"待开票","待打印","已完成","已作废"}));//数据分组 
		List<String> category = new ArrayList<String>(Arrays.asList(new String []{"专用发票","普通发票","电子发票"}));//横坐标
		
		
		List<Integer> dkp = fpService.queryInvoiceNum("1");
		List<Integer> ddy = fpService.queryInvoiceNum("2");
		List<Integer> wc = fpService.queryInvoiceNum("3");
		List<Integer> zf = fpService.queryInvoiceNum("-2");
		
		List<Series> series = new ArrayList<Series>();//纵坐标  
		
		series.add(new Series("待开票", "bar", dkp));
		series.add(new Series("待打印", "bar", ddy));
		series.add(new Series("已完成", "bar", wc));
		series.add(new Series("已作废", "bar", zf));
		
		EchartData data=new EchartData(legend, category, series);  
		
		/*if(list != null){
			for(int i = 0;i<list.size();i++){
				Map<String, Object> temp = list.get(i);
				
			}
		}*/
		return data;
	}
}
