package com.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reactive.handler.WebClient;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		WebClient webClient = new WebClient();
	      System.out.println(webClient.getResult());
	}
}
