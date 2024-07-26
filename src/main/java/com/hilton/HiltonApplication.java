package com.hilton;

import com.hilton.controller.GeoLocationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class HiltonApplication {
	private Logger log = LoggerFactory.getLogger(HiltonApplication.class);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@CacheEvict(allEntries = true, cacheNames = "geoLocationsCache" )
	@Scheduled(fixedDelay = 60000)
	public void cacheEvict() {
		log.info("cacheEvict() Clearing cache in 60000ms");
	}

	public static void main(String[] args) {
		SpringApplication.run(HiltonApplication.class, args);
	}

}
