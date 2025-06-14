package com.ecommerceproject.utils;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.util.Arrays;

@Configuration
@Component
public class MongoConfig {

	private String MONGO_DB_PASSWORD = "";
	private String MONGO_USER = "";
	private String MONGO_URI = "";

	// This method will be called to load environment variables
	@PostConstruct
	public void loadEnvVariables() {
		MONGO_DB_PASSWORD = System.getenv("MONGO_DB_PASSWORD");
		MONGO_USER = System.getenv("MONGO_USER");
		MONGO_URI = System.getenv("MONGO_URI");

		// You can log the values or handle missing environment variables here
		if (MONGO_USER == null || MONGO_DB_PASSWORD == null || MONGO_URI == null) {
			System.err.println("Warning: Missing environment variables for MongoDB connection!");
		}
	}

	// Getter methods to access the environment variables
	public String getMONGO_DB_PASSWORD() {
		return MONGO_DB_PASSWORD;
	}

	public String getMONGO_USER() {
		return MONGO_USER;
	}

	public String getMONGO_URI() {
		return MONGO_URI;
	}

	// MongoTemplate for ProductImages
	@Bean(name = "mongoTemplate")
	public MongoTemplate mongoTemplate() {
		String uri = String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin",
				MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
		MongoClient mongoClient = MongoClients.create(uri);
		return new MongoTemplate(mongoClient, "ProductImages");
	}

	// MongoTemplate for ProductImages (with specific template name)
	@Bean(name = "productImagesMongoTemplate")
	public MongoTemplate productImagesMongoTemplate() {
		String uri = String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin",
				MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
		MongoClient mongoClient = MongoClients.create(uri);
		return new MongoTemplate(mongoClient, "ProductImages");
	}

	// MongoTemplate for ProductDesc (with specific template name)
	@Bean(name = "productDescMongoTemplate")
	public MongoTemplate productDescMongoTemplate() {
		// Mongo URI for ProductDesc collection
		String uri = String.format("mongodb://%s:%s@%s:27017/Products_desc?authSource=admin",
				MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
		MongoClient mongoClient = MongoClients.create(uri);


		// Spring Boot will handle MongoMappingContext and MongoTemplate auto-configuration for you.
		return new MongoTemplate(mongoClient, "Products_desc");
	}




	// Helper method to build the Mongo URI
	private String getMongoUri() {
		return String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin",
				MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
	}
}
