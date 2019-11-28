package com.zyf.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	/**
	 * restTemplate负载均衡访问微服务
	 */
	@Bean("servcieRestTemplate")
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
