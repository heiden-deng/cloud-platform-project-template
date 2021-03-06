package com.heiden.dbp.zuul.filter;

import org.springframework.context.annotation.Configuration;

/**
 * 该类负责添加过滤器、servlet的配置等信息，所有的自定义的servlet、filter以及listner都在这儿进行定义
 * 
 * @author heiden
 *
 */
@Configuration("systemConfiguration")
public class SystemConfiguration {

  /**
   * 向容器中添加一个过滤器
   * 
   * @return
   */
  /*
   * @Bean public FilterRegistrationBean dbpFilter() { FilterRegistrationBean registration = new
   * FilterRegistrationBean(); //registration.setFilter(new RestFilter()); List<String> urlPatterns
   * = new ArrayList<String>(); // 拦截所有的URL请求 urlPatterns.add("/*");
   * registration.setUrlPatterns(urlPatterns); return registration; }
   */

}
