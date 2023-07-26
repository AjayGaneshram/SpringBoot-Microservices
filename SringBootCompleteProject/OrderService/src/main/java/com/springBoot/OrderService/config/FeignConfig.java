package com.springBoot.OrderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springBoot.OrderService.External.client.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
}

