/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

import java.util.List;

/**
  * @ClassName: Series
  * @Description: 封装Echart的纵坐标数据
  * @author zhaowei
  * @date 2016年3月21日 下午12:01:18
  */
public class Series {

	private String name; 
	
    private String type;  
      
    private List<Integer> data;//这里要用int 不能用String 不然前台显示不正常（特别是在做数学运算的时候）  
  
    public Series( String name, String type, List<Integer> data) {  
        super();  
        this.name = name;  
        this.type = type;  
        this.data = data;  
    }

	public String getName() {
	
		return name;
	}

	public void setName(String name) {
	
		this.name = name;
	}

	public String getType() {
	
		return type;
	}

	public void setType(String type) {
	
		this.type = type;
	}

	public List<Integer> getData() {
	
		return data;
	}

	public void setData(List<Integer> data) {
	
		this.data = data;
	}  
    
    
}
