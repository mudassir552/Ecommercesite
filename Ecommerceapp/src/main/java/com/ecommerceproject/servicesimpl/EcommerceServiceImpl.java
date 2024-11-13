package com.ecommerceproject.servicesimpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;
import com.ecommerceproject.services.EcommerceService;
import org.springframework.beans.factory.annotation.Qualifier;

import org.bson.Document;

@Service
public class EcommerceServiceImpl implements EcommerceService{
	
	
	

	private final MongoTemplate mongoTemplate;  // For productImagesMongoTemplate
	private final MongoTemplate mongoTemplate2; // For productDescMongoTemplate

	public EcommerceServiceImpl(
	    @Qualifier("productImagesMongoTemplate") MongoTemplate mongoTemplate,
	    @Qualifier("productDescMongoTemplate") MongoTemplate mongoTemplate2) {

	    this.mongoTemplate = mongoTemplate;
	    this.mongoTemplate2 = mongoTemplate2;
	}
	
	
    @Override
	public List<Products> getFirsttenproducts() {
    	  Pageable pageable = PageRequest.of(0, 8);  // Correct page index
    	    Query query = new Query().with(pageable);
		
	
		
	  List<Products> ProductsList = mongoTemplate2.find(query, Products.class);
	  
	  
	  System.out.println("Fetched products: " + ProductsList.size());  // Log the size
	  ProductsList.stream().forEach(x->System.out.println(x));
	  
	  return  ProductsList;
		

	}

    


	public List<ProductsDto> findProductsByCategory(String category) {
		// TODO Auto-generated method stub
		
		List<ProductsDto>poductdtos=new ArrayList<>();
		 Query query = new Query();
	        // Add criteria to the query
	        query.addCriteria(Criteria.where("productCategory").is(category));
	        
	        
	        
	        List<Products>products=mongoTemplate2.find(query, Products.class);
	        
	        
	   
	        for(Products product :products) {
	       String productImages = findImagesByProductId(product.getProductId());
           
	            
	       ProductsDto proddto=new ProductsDto();
	        	
	        	proddto.setBrand(product.getBrand());
	        	proddto.setImageId(product.getImageIds().get(0));
	        	proddto.setProductCategory(product.getProductCategory());
	        	proddto.setProductDesc(product.getProductDesc());
	        	proddto.setProductName(product.getProductName());
	        	proddto.setProductRating(product.getProductRating());
	        	proddto.setPrice(product.getPrice());
	        	proddto.setProductRating(product.getProductRating());
	        	proddto.setImageUrl(productImages);
	        	poductdtos.add(proddto);
	        	
	        	
	        	
	        }
	        
	        // Execute the query
	        return poductdtos;
		
	
	
		   
	        
	}
	
	// Make MongoClient a static final object to reuse it
	//private  final String uri = String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin", 
			//MongoConfig.getMONGO_USER() ,MongoConfig.getMONGO_DB_PASSWORD(),MongoConfig.getMONGO_URI());
	//private  final MongoClient mongoClient = MongoClients.create(uri);

	private String findImagesByProductId(String productId) {
		  Query query = new Query(Criteria.where("productId").is(productId));

	        // Add projection to only fetch the 'imageUrl' field (avoid fetching unnecessary data)
	        query.fields().include("imageUrl");

	        // Use findOne to get the first matching document
	        Document result = mongoTemplate.findOne(query, Document.class, "Images");

	        // If a document is found, return the image URL
	        if (result != null) {
	            return result.getString("imageUrl");
	        }

	        // If no document is found, return null
	        return null;
	    }


}
