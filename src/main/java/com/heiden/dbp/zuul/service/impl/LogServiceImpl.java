package com.heiden.dbp.zuul.service.impl;

import java.util.List;
import java.util.Map;

import com.heiden.dbp.zuul.dao.entity.Log;
import com.heiden.dbp.zuul.dao.mapper.LogMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heiden.dbp.zuul.service.LogService;
import com.heiden.dbp.zuul.utils.TimeUtil;
import com.heiden.dbp.zuul.vo.request.LogVo;
import com.heiden.dbp.zuul.vo.response.LogResponseResult;


@Service
public class LogServiceImpl implements LogService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

	private String responseSuccessStatus = "200";

	private String responseFailedStatus = "400";

	private String databaseTimeTemplateDay = "%Y-%m-%d";

	private String databaseTimeTemplateHour = "%Y-%m-%d %H";

	private String databaseTimeTemplateMinute = "%Y-%m-%d %H:%i";

	@Autowired
	private LogMapper logMapper;

	@Override
	public Object searchLog(LogVo logVo) throws Exception {
		return null;
	}

	@Override
	public Object subsectionStatsLog(LogVo logVo) {
		return null;
	}

	@Override
	public Object avgResponseTimeLog(LogVo logVo) {
		return null;
	}

	private List<Map> getLogInfo(LogVo logVo, Log log, String[] apiId) {
		List<Map> loginfo = null;
		String timeTemple = "";
		// 分
		if ("0".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateMinute;
			// 时
		} else if ("1".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateHour;
			// 天
		} else if ("2".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateDay;
		}

		if (StringUtils.isNotEmpty(timeTemple)) {
			loginfo = logMapper.findLogSubsectionStats(log, TimeUtil.stringToLong(logVo.getBeginTime()),
					TimeUtil.stringToLong(logVo.getEndTime()), logVo.getResponseStatus(), apiId, timeTemple);
		} else {
			loginfo = logMapper.findLogSubsectionStats(log, TimeUtil.stringToLong(logVo.getBeginTime()),
					TimeUtil.stringToLong(logVo.getEndTime()), logVo.getResponseStatus(), apiId,
					databaseTimeTemplateMinute);
		}
		return loginfo;
	}

	private List<Map> getAvgLogResposeTime(LogVo logVo, Log log, String[] apiId) {
		List<Map> loginfo = null;
		String timeTemple = "";
		// 分
		if ("0".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateMinute;
			// 时
		} else if ("1".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateHour;
			// 天
		} else if ("2".equals(logVo.getShowState())) {
			timeTemple = databaseTimeTemplateDay;
		}
		if (StringUtils.isNotEmpty(timeTemple)) {
			loginfo = logMapper.findavgResponseTimeLog(log, TimeUtil.stringToLong(logVo.getBeginTime()),
					TimeUtil.stringToLong(logVo.getEndTime()), logVo.getResponseStatus(), apiId, timeTemple);
		} else {
			loginfo = logMapper.findavgResponseTimeLog(log, TimeUtil.stringToLong(logVo.getBeginTime()),
					TimeUtil.stringToLong(logVo.getEndTime()), logVo.getResponseStatus(), apiId,
					databaseTimeTemplateMinute);

		}
		return loginfo;
	}

}
