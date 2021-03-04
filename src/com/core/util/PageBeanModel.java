package com.core.util;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import com.google.gson.annotations.Expose;


public class PageBeanModel<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;
    private long total;        //总记录数
    private List<T> rows;    //结果集
    
    
    
    
	public PageBeanModel(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
    
}