package org.maia.hrpayroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableCircuitBreaker // hystrix
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ApiHrPayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHrPayrollApplication.class, args);
	}

}
