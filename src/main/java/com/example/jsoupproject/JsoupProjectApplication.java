package com.example.jsoupproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsoupProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsoupProjectApplication.class, args);
	}

}
