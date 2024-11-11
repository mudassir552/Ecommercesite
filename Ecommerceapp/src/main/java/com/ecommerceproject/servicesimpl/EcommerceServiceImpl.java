package com.ecommerceproject.servicesimpl;

import java.util.ArrayList;
import  com.ecommerceproject.repository.ImageRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ecommerceproject.products.ProductImages;
import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;
import com.ecommerceproject.services.EcommerceService;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ecommerceproject.utils.MongoConfig;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import jakarta.annotation.PostConstruct;
import org.bson.Document;
@Service
public class EcommerceServiceImpl implements EcommerceService{
	
	String MONGO_DB_PASSWORD="";
	String MONGO_USER="";
	String MONGO_URI="";
	@PostConstruct
	public void checkEnvVariables() {
	
		MONGO_DB_PASSWORD=System.getenv("MONGO_DB_PASSWORD");
		MONGO_USER=System.getenv("MONGO_USER");
		MONGO_URI=System.getenv("MONGO_URI");
	
	}
	
	  @Autowired
	    @Qualifier("productsMongoOperations")
	    private MongoOperations productsMongoOperations;

	   
	    @Autowired
	    @Qualifier("productImagesMongoOperations")
	    private MongoOperations productImagesMongoOperations;

	  @Autowired
	  private ImageRepository ImageRepository;
	    
	  @Autowired
     private com.ecommerceproject.repository.Ecommercerepo Ecommercerepo;
	  
	  
	 
	
	
    @Override
	public List<Products> getFirsttenproducts() {
    	  Pageable pageable = PageRequest.of(0, 8);  // Correct page index
    	    Query query = new Query().with(pageable);
		
	
		
	  List<Products> ProductsList = productsMongoOperations.find(query, Products.class);
	  
	  
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
	        
	        
	        
	        List<Products>products=productsMongoOperations.find(query, Products.class);
	        
	        
	           products.stream().forEach(product-> System.out.println("Entered products"+product));
	   
	        for(Products product :products) {
	       String productImages = findImagesByProductId(product.getProductId());
           System.out.println("IMAGESSSS"+productImages);
	            
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
	
	private String findImagesByProductId(String productId) {
		
		System.out.println("MONGO_USER: " + System.getenv("MONGO_USER"));
	    System.out.println("MONGO_DB_PASSWORD: " + System.getenv("MONGO_DB_PASSWORD"));
	    System.out.println("MONGO_URI: " + System.getenv("MONGO_URI"));
		
		String imageUrl="";
		/*
		 * Query imageQuery = new Query();
		 * imageQuery.addCriteria(Criteria.where(productId).is(productId));
		 * 
		 * Document document = productImagesMongoOperations.findOne(imageQuery ,
		 * Document.class, "Images"); String productImage =
		 * document.getString("imageUrl");
		 * System.out.println("\033[32mENTERED IMAGESSS\033[0m");
		 * System.out.println("PIMAGESSSSS"+document.getString("imageUrl")); return
		 * productImage != null ? productImage: null;
		 */
	    
		String uri = String.format("mongodb://%s:%s@%s:27017/ProductImages?authSource=admin", 
                MONGO_USER, MONGO_DB_PASSWORD, MONGO_URI);
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("ProductImages"); // Get the database
        MongoCollection<Document> collection = database.getCollection("Images");
      

        // Create query for productId
        Document query = new Document("productId", productId);

        // Use findOne for better performance (only gets the first match)
        Document document = collection.find(query).first();

        
        // Iterate through the results and print the 'imageUrl' field
		
        if (document != null) {
            imageUrl = document.getString("imageUrl");
            System.out.println("\033[32mENTERED IMAGESSS\033[0m");
            System.out.println("PIMAGESSSSS: " + imageUrl);
        }

        
		  
	
         return imageUrl;

}
}
