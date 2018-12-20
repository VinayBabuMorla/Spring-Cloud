package io.schultz.dustin.ribbontimeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.schultz.config.dustin.ribbontimeapp.RibbonTimeConfig;

@SpringBootApplication
//@EnableDiscoveryClient //Not required when @RibbonClinet is used
@RibbonClient(name="time-service", configuration=RibbonTimeConfig.class)
@RestController
public class RibbonTimeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeAppApplication.class, args);
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced //Improves the distribution of workloads across multiple computing resources   
	public RestTemplate resttTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping
	@HystrixCommand
	public String getTime() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}
}

