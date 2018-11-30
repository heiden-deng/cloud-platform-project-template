package com.heiden.dbp.zuul.vo.response;

import com.heiden.dbp.zuul.dao.entity.Log;

import java.io.Serializable;
import java.util.List;

/**
 * @author gg
 *
 */
public class LogResponseResult implements Serializable{
       
	
	private static final long serialVersionUID = 1L;

	
	private List<Log> successList;
	
	private Integer successSzie;
	
	private List<Log> failedList;
	
	private Integer failedSzie;
	
	private List<Log> logTotalList;
	
	private Integer logTotalSize;
	
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
	

	public List<Log> getSuccessList() {
		return successList;
	}

	public Integer getSuccessSzie() {
		return successSzie;
	}

	public List<Log> getFailedList() {
		return failedList;
	}

	public Integer getFailedSzie() {
		return failedSzie;
	}

	public List<Log> getLogTotalList() {
		return logTotalList;
	}

	public void setSuccessList(List<Log> successList) {
		this.successList = successList;
	}

	public void setSuccessSzie(Integer successSzie) {
		this.successSzie = successSzie;
	}

	public void setFailedList(List<Log> failedList) {
		this.failedList = failedList;
	}

	public void setFailedSzie(Integer failedSzie) {
		this.failedSzie = failedSzie;
	}


	public void setLogTotalList(List<Log> logTotalList) {
		this.logTotalList = logTotalList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public long getTotal() {
		return total;
	}

	public int getPages() {
		return pages;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Integer getLogTotalSize() {
		return logTotalSize;
	}

	public void setLogTotalSize(Integer logTotalSize) {
		this.logTotalSize = logTotalSize;
	}


	
	
	
	
}
