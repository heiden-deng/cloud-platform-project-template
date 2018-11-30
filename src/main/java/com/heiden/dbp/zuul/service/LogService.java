package com.heiden.dbp.zuul.service;


import com.heiden.dbp.zuul.vo.request.LogVo;

public interface LogService {

	public Object searchLog (LogVo logVo) throws Exception;

	public Object subsectionStatsLog(LogVo logVo);

	public Object avgResponseTimeLog(LogVo logVo);

}
