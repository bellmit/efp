/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.einvoice.qz.beans;

/**
  * @ClassName: Page
  * @Description: 分页实体类
  * @author zhaowei
  * @date 2015年8月12日 上午10:09:38
  */
public class Page{

	private int pageSize =  2;

    private int pageIndex = 0;

    private int totalCounts = 0;
    private int totalPages = 0;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

	public int getPageIndex() {
	
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
	
		this.pageIndex = pageIndex;
	}
    
    
}
