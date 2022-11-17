package com.unab.apipartidosg30;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unab.apipartidosg30.utils.AppContexto;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) 
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 
@SpringBootApplication
@EnableJpaAuditing
public class Apipartidosg30Application {

	public static void main(String[] args) {
		SpringApplication.run(Apipartidosg30Application.class, args);
		System.out.println("Api Corriendo...");

		//SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS512);
		//String base64key= Encoders.BASE64.encode(key.getEncoded());
		//System.out.println(base64key);
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

	@Bean
	public AppContexto appContexto(){
		return new AppContexto();
	}
}
