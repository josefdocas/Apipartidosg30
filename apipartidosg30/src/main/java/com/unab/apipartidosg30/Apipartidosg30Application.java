package com.unab.apipartidosg30;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) 
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 
//@SpringBootApplication()
public class Apipartidosg30Application {

	public static void main(String[] args) {
		SpringApplication.run(Apipartidosg30Application.class, args);
		System.out.println("Api Corriendo...");
	}

	@Bean
	public ModelMapper modelMapper (){
		ModelMapper modelMapper =new ModelMapper();
	
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return modelMapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		
		return new BCryptPasswordEncoder();
	}
}
