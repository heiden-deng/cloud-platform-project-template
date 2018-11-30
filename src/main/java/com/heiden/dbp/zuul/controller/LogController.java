package com.heiden.dbp.zuul.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.heiden.dbp.zuul.service.LogService;
import com.heiden.dbp.zuul.vo.request.LogVo;
import com.heiden.dbp.zuul.vo.response.RequestResult;

import io.swagger.annotations.ApiOperation;

/**
 * @author
 *
 */
@Controller
public class LogController extends AbsController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
	

	 @Autowired
	 private LogService logService;
	/**
	 * 统计该时段内总的访问详情
	 * @throws Exception 
	 */
	@ApiOperation(value="统计日志信息",notes="统计日志信息",response= RequestResult.class)
	@RequestMapping(value="/v1/statisticsLog",method=RequestMethod.POST)
	public ModelAndView logStats(@RequestBody LogVo logVo) throws Exception{
		LOGGER.debug("statistics log");
		RequestResult requestResult=null;
		if(StringUtils.isEmpty(logVo.getUsername()) && StringUtils.isEmpty(logVo.getUrl())){
			LOGGER.debug("url or username is not empty !");
			return super.createModelAndView(new RequestResult("url or username is not empty !"));
		}
		requestResult = new RequestResult(logService.searchLog(logVo));
		return super.createModelAndView(requestResult);
	}
	
	
	/**
	 * 统计时段成功失败访问详情及次数
	 */
	@ApiOperation(value="按时段统计日志信息",notes="按时段统计日志信息",response= RequestResult.class)
	@RequestMapping(value="/v1/logSubsectionStats",method=RequestMethod.POST)
	public ModelAndView logSubsectionStats(@RequestBody LogVo logVo){
		LOGGER.debug("subsection stats log");
 		RequestResult requestResult=null;
		if(StringUtils.isEmpty(logVo.getUsername()) && StringUtils.isEmpty(logVo.getUrl())){
			LOGGER.debug("url or username is not empty !");
			return super.createModelAndView(new RequestResult("url or username is not empty !"));
		}
		if(StringUtils.isEmpty(logVo.getBeginTime())){
			LOGGER.debug("begin time is not empty !");
			return super.createModelAndView(new RequestResult("begin time is not empty !"));
		}
		requestResult = new RequestResult(logService.subsectionStatsLog(logVo));
		return super.createModelAndView(requestResult);
		
		
	}
	
	
	/**
	 * 统计各时段平均响应时间
	 */
	
	@ApiOperation(value="按时段统计平均响应时间",notes="按时段统计平均响应时间",response= RequestResult.class)
	@RequestMapping(value="/v1/logAvgResponseTime",method=RequestMethod.POST)
	public ModelAndView logAvgResponseTime(@RequestBody LogVo logVo){
		LOGGER.debug("subsection stats log");
		RequestResult requestResult=null;
		if(StringUtils.isEmpty(logVo.getUsername()) && StringUtils.isEmpty(logVo.getUrl())){
			LOGGER.debug("url or username is not empty !");
			return super.createModelAndView(new RequestResult("url or username is not empty !"));
		}
		if(StringUtils.isEmpty(logVo.getBeginTime())){
			LOGGER.debug("begin time is not empty !");
			return super.createModelAndView(new RequestResult("begin time is not empty !"));
		}
		requestResult = new RequestResult(logService.avgResponseTimeLog(logVo));
		return super.createModelAndView(requestResult);
	}
	
	
}
