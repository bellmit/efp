/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName: EchartData
  * @Description: 封装Echart图表数据
  * @author zhaowei
  * @date 2016年3月21日 上午11:59:03
  */
public class EchartData {

	private List<String> legend = new ArrayList<String>();//数据分组  
	private List<String> category = new ArrayList<String>();//横坐标  
	private List<Series> series = new ArrayList<Series>();//纵坐标  
      
      
    public EchartData(List<String> legendList, List<String> categoryList, List<Series> seriesList) {  
        super();  
        this.legend = legendList;  
        this.category = categoryList;  
        this.series = seriesList;  
    }


	public List<String> getLegend() {
	
		return legend;
	}


	public void setLegend(List<String> legend) {
	
		this.legend = legend;
	}


	public List<String> getCategory() {
	
		return category;
	}


	public void setCategory(List<String> category) {
	
		this.category = category;
	}


	public List<Series> getSeries() {
	
		return series;
	}


	public void setSeries(List<Series> series) {
	
		this.series = series;
	}  
    
    
}
