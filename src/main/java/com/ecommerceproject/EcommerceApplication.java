package com.ecommerceproject;
import com.ecommerceproject.utils.MongoConfig;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import  com.ecommerceproject.repository.Ecommercerepo;
import  com.ecommerceproject.repository.ImageRepository;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableCaching
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackageClasses={Ecommercerepo.class,ImageRepository.class})
@Component(value = "com.ecommerceproject.*")

public class EcommerceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
