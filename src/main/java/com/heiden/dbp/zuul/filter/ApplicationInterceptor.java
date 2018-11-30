package com.heiden.dbp.zuul.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 类名: ApplicationInterceptor <br/>
 * <br/>
 * 需要在/config/ServletContextConfig 注册拦截器
 * 
 * @author heiden
 * 
 */
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationInterceptor.class);

  /**
   * 在执行方法中的逻辑之前执行
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    LOG.debug("在执行方法中的逻辑之前执行");
    return true;
  }

  /**
   * 执行主体的业务逻辑之后执行
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    LOG.debug("在执行方法中的逻辑之后执行");

    if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
      if (!(request instanceof RestHttpServletRequestWrapper)) {
        return;
      }

      if (modelAndView != null) {
        Map<String, Object> map = modelAndView.getModel();
        String data = (String) map.get("data");
        String status = (String) map.get("status");
        LOG.debug("status：{}   data:{}", status, data);
      }


    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {

  }

}
