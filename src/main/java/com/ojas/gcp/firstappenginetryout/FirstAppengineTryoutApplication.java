package com.ojas.gcp.firstappenginetryout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication (exclude = { ErrorMvcAutoConfiguration.class })
//@EnableJpaRepositories(basePackages = {"com.ojas.gcp.firstappenginetryout.repository"})
//@EntityScan("com.ojas.gcp.firstappenginetryout.entity")
public class FirstAppengineTryoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppengineTryoutApplication.class, args);
	}
}
