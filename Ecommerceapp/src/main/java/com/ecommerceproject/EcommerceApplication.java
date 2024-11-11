package com.ecommerceproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import  com.ecommerceproject.repository.Ecommercerepo;
import  com.ecommerceproject.repository.ImageRepository;



@SpringBootApplication

@EnableMongoRepositories(basePackageClasses={Ecommercerepo.class,ImageRepository.class})
public class EcommerceApplication {
	
	
	

	
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


    
	

}
