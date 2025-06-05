package com.ecommerceproject.products;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "ProductImages")
public class ProductImages {
	
    @Id
    private String id;
    @Field("productId") 
    private String productId; 
    @Field("imageUrl") 
    private String imageUrl;
    
    
    
	public ProductImages(String id, String productId, String imageUrl) {
		super();
		this.id = id;
		this.productId = productId;
		this.imageUrl = imageUrl;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
   
    
    
    
    
  
}