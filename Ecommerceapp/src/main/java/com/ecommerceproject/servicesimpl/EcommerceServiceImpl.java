package com.ecommerceproject.servicesimpl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;
import org.bson.Document;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;
import com.ecommerceproject.repository.Ecommercerepo;
import com.ecommerceproject.services.EcommerceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;


@Service
public class EcommerceServiceImpl implements EcommerceService{
	
	
	@Autowired
	private com.ecommerceproject.repository.Ecommercerepo Ecommercerepo;	
 
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
	  
	  
	 
	  
	  return  ProductsList;
		

	}

    

    @Cacheable(value = "products", key = "#category")
    public List<ProductsDto> findProductsByCategory(String category) {
        // Create the list of ProductsDto to return
        List<ProductsDto> productDtos = new ArrayList<>();

        // Step 1: Create the query to find products by category
        Query query = new Query(Criteria.where("productCategory").is(category));

        // Step 2: Execute the query to fetch products
        List<Products> products = mongoTemplate2.find(query, Products.class);

        // Step 3: Extract the list of product IDs from the products list to find their images
        List<String> productIds = products.stream()
                                          .map(Products::getProductId)
                                          .collect(Collectors.toList());

        // Step 4: Fetch images for all product IDs in a single query
        List<Document> imageDocuments = findImagesByProductIds(productIds);

        // Step 5: Create a map to quickly look up image URLs by product ID
        Map<String, List<String>> productImagesMap = new HashMap<>();
        for (Document imageDoc : imageDocuments) {
            String productId = imageDoc.getString("productId");
            String imageUrl = imageDoc.getString("imageUrl");
            if (productId != null && imageUrl != null) {
                productImagesMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(imageUrl);
            }
        }

        // Step 6: Populate the DTOs with product and image data
        for (Products product : products) {
        	
        	System.out.println("PAIKNA"+product.getProductId());
            ProductsDto productDto = new ProductsDto();
            productDto.setProductId(product.getProductId());
            productDto.setBrand(product.getBrand());
            productDto.setImageId(product.getImageIds().get(0)); // Assuming the first imageId as primary image
            productDto.setProductCategory(product.getProductCategory());
            productDto.setProductDesc(product.getProductDesc());
            productDto.setProductName(product.getProductName());
            productDto.setProductRating(product.getProductRating());
            productDto.setPrice(product.getPrice());

            // Fetch image URLs from the map for the current product ID
            List<String> productImages = productImagesMap.get(product.getProductId());
            if (productImages != null && !productImages.isEmpty()) {
                productDto.setImageUrl(productImages.get(0)); // Taking the first image URL as primary image
            } else {
                productDto.setImageUrl(null); // No images found
            }

            // Add the populated DTO to the list
            productDtos.add(productDto);
        }

        return productDtos;
    }

    // Modify the findImagesByProductIds method to batch fetch images for multiple products
    private List<Document> findImagesByProductIds(List<String> productIds) {
        // Create a query to find documents by productId (in a batch)
        Query query = new Query(Criteria.where("productId").in(productIds));

        // Add projection to only fetch the 'productId' and 'imageUrl' fields (avoid fetching unnecessary data)
        query.fields().include("productId").include("imageUrl");

        // Use find to get all matching documents
        return mongoTemplate.find(query, Document.class, "Images");
    }

    public Products saveProductRating(Products proddto) throws Exception {
        // Create a query to search by productId
    	Query query = new Query(Criteria.where("productId").is(proddto.getProductId()));
    	Update update = new Update().set("productRating", proddto.getProductRating());

    	// Update the product's rating in the database
    	
        
        System.out.println("SUBMITTED Rating for Product ID: " + proddto.getProductId());
        
        // Find the product by productId
        Products product = mongoTemplate2.findOne(query, Products.class);
        
        
        if (product != null) {
            // Set the new product rating
            product.setProductRating(proddto.getProductRating());
            System.out.println("RATTTT"+product.getProductRating());
            // Save the updated product to the repository
            mongoTemplate2.updateFirst(query, update, Products.class);
            
            // Optionally, return the updated DTO
            proddto.setProductRating(product.getProductRating());
            return proddto;  // Returning the updated DTO
        } else {
            // Throwing an exception if product not found
            throw new Exception("Product with Id " + proddto.getProductId() + " not found");
        }
    }
    	  
    	  
      }


