package com.heiden.dbp.zuul.error;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.heiden.dbp.zuul.exception.UserException;
import com.heiden.dbp.zuul.vo.response.RequestResult;
import com.netflix.zuul.exception.ZuulException;

@RestController
public class MainsiteErrorController implements ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainsiteErrorController.class);

  @Resource(name = "errorAttributes")
  private final ErrorAttributes errorAttributes;

  @Value("${system.internal.show}")
  private Boolean internalShow;

  public MainsiteErrorController(ErrorAttributes errorAttributes) {
    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
    this.errorAttributes = errorAttributes;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping(value = "/error")
  @ResponseBody
  public ResponseEntity<RequestResult> error(HttpServletRequest request) {
    Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
    RequestResult result = new RequestResult();
    Exception exception = (Exception) body.get("exception");
    if (exception != null) {
      LOGGER.error("exceptions={}", exception);
    }
    String requestId = request.getParameter("requestId");
    String responseId = request.getParameter("responseId");

    String exceptionMsg = UserException.MAINTAINING;
    if (exception instanceof UserException) {
      UserException userException = (UserException) exception;
      exceptionMsg = userException.getMsg();
    } else if (exception instanceof ZuulException) {
      ZuulException zuulException = (ZuulException) exception;
      exceptionMsg = zuulException.errorCause;
    } else {
      if (body.get("error") != null) {
        exceptionMsg = (String) body.get("error");
      }
    }
    /*
     * Map<String, List<String>> errorMsg = new HashMap<String, List<String>>(); List<String>
     * errorMessageList = new ArrayList<String>(); errorMsg.put("object.error.message",
     * errorMessageList); errorMessageList.add(exceptionMsg); result.setErrorMsg(errorMsg);
     * LOGGER.error("[internalShow={}]", internalShow); if (internalShow && exception != null) {
     * result.setInternalErrorMsg(exception.getMessage()); }
     */
    if (body.get("timestamp") != null) {
      result.setTimestamp((String)body.get("timestamp"));
    }
    if (StringUtils.isNotBlank(requestId)) {
      result.setRequestId(requestId);
    } else {
      result.setRequestId(StringUtils.EMPTY);
    }
    if (StringUtils.isNotBlank(responseId)) {
      result.setResponseId(responseId);
    } else {
      result.setResponseId(StringUtils.EMPTY);
    }
    if (body.get("status") != null) {
      result.setStatus((Integer) body.get("status"));
    }
    result.setMessage(exceptionMsg);

    HttpStatus status = getStatus(request);
    if (status.is4xxClientError()) {// 如果为4错误，需要打印出来，方便快速定位问题，4开头错误为无法找到地址，一般为用户发送的请求地址错误
      LOGGER.error("打印响应的内容数据");
      for (Map.Entry<String, Object> entry : body.entrySet()) {
        if (!"message".equals(entry.getKey())) {
          LOGGER.error("[{}={}]", entry.getKey(), entry.getValue());
        }
      }
    }
    if (exception != null) {
      LOGGER.error("提示用户的信息={}", exception.getMessage());
    }
    return new ResponseEntity<RequestResult>(result, status);
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request,
      boolean includeStackTrace) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode != null) {
      try {
        return HttpStatus.valueOf(statusCode);
      } catch (Exception ex) {
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  private boolean getTraceParameter(HttpServletRequest request) {
    String parameter = request.getParameter("trace");
    if (parameter == null) {
      return false;
    }
    return !"false".equals(parameter.toLowerCase());
  }

}
