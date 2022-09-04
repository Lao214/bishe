package com.example.mymusic.common.pages;

import java.io.Serializable;


public class PageParamEntity implements Serializable {
	 private static final long serialVersionUID = -8445500869821839109L;

	 private int start = 0;

  	  private int limit = 10;

	  public PageParamEntity()
	  {
	  }
	
	  public PageParamEntity(int start, int limit)
	  {
		  this.start = (start <= 0 ? 0 : start);
		  this.limit = (limit <= 0 ? 10 : limit);
	  }
	
	  public int getStart()
	  {
		  return this.start;
	  }
	
	  public void setStart(int start)
	  {
		  this.start = (start <= 0 ? 0 : start);
	  }
	
	  public int getLimit()
	  {
		  return this.limit;
	  }
	
	  public void setLimit(int limit)
	  {
		  this.limit = (limit <= 0 ? 10 : limit);
	  }
}
