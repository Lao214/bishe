package com.example.mymusic.common;
  
import com.example.mymusic.common.pages.IPage;
import com.example.mymusic.common.pages.PageParamEntity;

import java.util.ArrayList;
import java.util.Collection;

public class Page<T> implements IPage<T> {
	
    private static final long serialVersionUID = -6570192533192942163L;
    private int start;
    private int limit;
    private long total;
    private Collection<T> rows;
    private Object otherData;
  
    public Page()
    {
    }
  
    public Page(Collection<T> rows, long total, int start, int limit)
    {
    	this.start = start;
     	this.limit = limit;
     	this.total = total;
     	this.rows = (rows == null ? new ArrayList<>(0) : rows);
    }
  
    public Page(Collection<T> rows, long total, int start, int limit, Object otherData)
    {
	     this.start = start;
	     this.limit = limit;
	     this.total = total;
	     this.rows = (rows == null ? new ArrayList<>(0) : rows);
	     this.otherData = otherData;
    }
  
    public Page(Collection<T> rows, long total, PageParamEntity param)
    {
	     if (param != null) {
	    	 this.start = param.getStart();
	    	 this.limit = param.getLimit();
	     }
	     this.total = total;
	     this.rows = (rows == null ? new ArrayList<>(0) : rows);
    }
  
    public Page(Collection<T> rows, long total, PageParamEntity param, Object otherData)
    {
    	if (param != null) {
    		this.start = param.getStart();
    		this.limit = param.getLimit();
    	}
    	this.total = total;
    	this.rows = (rows == null ? new ArrayList<>(0) : rows);
    	this.otherData = otherData;
    }
  
    public int getStart() {
    	return this.start;
    }
  
    public void setStart(int start) {
    	this.start = start;
    }
  
    public int getLimit() {
    	return this.limit;
    }
  
    public void setLimit(int limit) {
    	this.limit = limit;
    }
  
    public long getTotal() {
    	return this.total;
    }
  
    public void setTotal(long total) {
    	this.total = total;
    }
  
    public Collection<T> getRows() {
    	return this.rows;
    }
  
    public void setRows(Collection<T> rows) {
    	this.rows = rows;
    }
  
    public Object getOtherData() {
    	return this.otherData;
    }
  
    public void setOtherData(Object otherData) {
    	this.otherData = otherData;
    }
  
    public String toString()
    {
    	return "Page [start=" + this.start + ", limit=" + this.limit + ", total=" + this.total + ", rows=" + this.rows + ", otherData=" + this.otherData + "]";
    }
}

