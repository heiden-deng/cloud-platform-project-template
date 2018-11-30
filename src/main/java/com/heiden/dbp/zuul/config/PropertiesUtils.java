package com.heiden.dbp.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
@ConfigurationProperties(prefix = "custom") 
public class PropertiesUtils {


	private int redisExpireTime;
	
	private boolean enabled;
	
	private int refreshTime;

	
}
