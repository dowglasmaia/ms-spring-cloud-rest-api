package org.maia.hreurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApiHrEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHrEurekaServerApplication.class, args);
	}

}
