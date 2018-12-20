package io.schultz.dustin.ribbontimeservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableDiscoveryClient //Not required when @RibbonClinet is used
@RestController
public class RibbonTimeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeServiceApplication.class, args);
	}

	@Value("${server.port}")	
	private int port;
	
	@GetMapping
	public String getTime() {
		return "The current time is "+new Date().toString()+
				"(Answered by service running on "+port+")";
	}
}

