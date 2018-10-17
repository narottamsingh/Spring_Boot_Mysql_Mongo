package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerApplication {

	Logger logger = Logger.getLogger(DockerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello() {
		return "Hello API";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String get() throws InterruptedException {
		for (int i = 0; i <= 10; i++) {
			logger.info("Hello");
			logger.error("hello error");
			Thread.sleep(1000);
		}
		return "Docker examples";
	}
}
