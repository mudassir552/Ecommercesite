package com.ecommerceproject.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import org.springframework.data.mongodb.core.MongoTemplate;
import jakarta.annotation.PostConstruct;

@Configuration
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
	
	
	
	
	
	 @Bean(name = "mongoTemplate")
	    public MongoTemplate mongoTemplate() {
		 
			String uri = String.format("mongodb://%s:%s@%s:27017/Products_desc?authSource=admin", 
                    MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
	        MongoClient mongoClient = MongoClients.create(uri);
	        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, "Products_desc"));
	    }
	
	@Bean(name = "productsMongoOperations")
    public MongoOperations productsMongoOperations() {
		String uri = String.format("mongodb://%s:%s@%s:27017/Products_desc?authSource=admin", 
                MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
        MongoClient mongoClient = MongoClients.create(uri);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, "Products_desc"));
    }

    // MongoTemplate for the "ProductImages" database (custom configuration)
    @Bean(name = "productImagesMongoOperations")
    public MongoOperations productImagesMongoOperations() {
    	String uri = String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin", 
                MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
        MongoClient mongoClient = MongoClients.create(uri);
      
        
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, "ProductImages"));
    }
}
