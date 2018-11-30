package com.heiden.dbp.zuul.vo.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "log请求对象", value = "log")
public class LogVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "logId", dataType = "String")
	private String id; // 
	@ApiModelProperty(value = "对应apiId", dataType = "String")
	private String url; // 
	@ApiModelProperty(value = "请求方法", dataType = "String")
	private String method; // 
	@ApiModelProperty(value = "后端服务地址", dataType = "String")
	private String location; // 
	@ApiModelProperty(value = "后端IP", dataType = "String")
	private String ip; // 
	@ApiModelProperty(value = "请求时间", dataType = "Long")
	private Long startTime; // 
	@ApiModelProperty(value = "响应时间", dataType = "Long")
	private Long stopTime; // 
	@ApiModelProperty(value = "响应时长", dataType = "Integer")
	private Integer requestTime; // 
	@ApiModelProperty(value = "响应状态", dataType = "Integer")
	private Integer status; // 
	@ApiModelProperty(value = "api路径", dataType = "String")
	private String path; // 
	@ApiModelProperty(value = "消息体", dataType = "String")
	private String msg; //
	@ApiModelProperty(value = "客户端标识", dataType = "String")
	private String client_id; //
	@ApiModelProperty(value = "查询开始日期 yyyy-MM-dd", dataType = "String")
	private String beginTime;
	@ApiModelProperty(value = "查询结束日期 yyyy-MM-dd", dataType = "String")
	private String endTime;
	@ApiModelProperty(value = "用户名", dataType = "String")
	private String username;
	@ApiModelProperty(value = "成功或失败状态,200 为成功,其他为失败", dataType = "String")
	private String responseStatus;
	@ApiModelProperty(value = "数据展示形式,0 分 1 小时 2 天", dataType = "String")
	private String showState;
	@ApiModelProperty(value = "当前页,默认0页", dataType = "Integer")
	private Integer pageNum=0;
	@ApiModelProperty(value = "每页条数,默认50条", dataType = "Integer")
	private Integer pageSize=50;
	
	

	public String getShowState() {
		return showState;
	}

	public void setShowState(String showState) {
		this.showState = showState;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getMethod() {
		return method;
	}

	public String getLocation() {
		return location;
	}

	public String getIp() {
		return ip;
	}

	public Long getStartTime() {
		return startTime;
	}

	public Long getStopTime() {
		return stopTime;
	}

	public Integer getRequestTime() {
		return requestTime;
	}

	public Integer getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}

	public String getMsg() {
		return msg;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}

	public void setRequestTime(Integer requestTime) {
		this.requestTime = requestTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
}
