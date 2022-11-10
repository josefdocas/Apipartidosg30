package com.unab.apipartidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication()
public class ApiPartidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPartidosApplication.class, args);
		System.out.println("Api Corriendo...");
	}

}
 