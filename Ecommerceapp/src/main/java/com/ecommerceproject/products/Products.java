package com.ecommerceproject.products;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;

@Document(collection = "Products") 
public class Products {
    
    @Id
    @Field("productId") 
    private String productId;  // Unique identifier

    private String productName;
    private String productCategory;
    private double price;
    
    private String productDesc;
   @Field("productRating")
    private String productRating;
    private String brand;
    
    private List<String> productReviews;   
 
    private List<String> imageIds; 

    //private List<ProductSpecification> productSpecifications;  // Embedded list of specifications

    public Products() {
    	
    }
    
   



	public Products(String productId, String productName, String productCategory, double price, String productDesc,
			String productRating, String brand, List<String> productReviews, List<String> imageIds) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.price = price;
		this.productDesc = productDesc;
		this.productRating = productRating;
		this.brand = brand;
		this.productReviews = productReviews;
		this.imageIds = imageIds;
	}



















	public String getProductId() {
		return productId;
	}




	public void setProductId(String productId) {
		this.productId = productId;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
	}




	public String getProductCategory() {
		return productCategory;
	}




	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public String getProductDesc() {
		return productDesc;
	}




	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}




	public String getProductRating() {
		return productRating;
	}




	public void setProductRating(String productRating) {
		this.productRating = productRating;
	}




	public String getBrand() {
		return brand;
	}




	public void setBrand(String brand) {
		this.brand = brand;
	}




	public List<String> getImageIds() {
		return imageIds;
	}




	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}




	








	public List<String> getProductReviews() {
		return productReviews;
	}





	public void setProductReviews(List<String> productReviews) {
		this.productReviews = productReviews;
	}





	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productCategory="
				+ productCategory + ", price=" + price + ", productDesc=" + productDesc + ", productRating="
				+ productRating + ", brand=" + brand + ", productReviews=" + productReviews + ", imageIds=" + imageIds
				+ "]";
	}




   
   
}
