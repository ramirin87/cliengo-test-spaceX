package com.cliengo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@EnableAsync
@SpringBootApplication
public class CliengoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliengoApplication.class, args);
	}


}
