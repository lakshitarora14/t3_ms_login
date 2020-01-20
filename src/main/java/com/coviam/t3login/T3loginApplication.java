package com.coviam.t3login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class T3loginApplication {

	public static void main(String[] args) {
		SpringApplication.run(T3loginApplication.class, args);
	}

}
