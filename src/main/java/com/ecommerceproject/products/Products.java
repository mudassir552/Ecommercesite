package com.ecommerceproject.products;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerceproject.products.dto.Reviews;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

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

    private List<Reviews> productReviews;
 
    private List<String> imageIds; 

    //private List<ProductSpecification> productSpecifications;  // Embedded list of specifications

    public Products() {
    	
    }


	public Products(String productCategory, String productId, String productName, double price, String productDesc, String productRating, String brand,  List<Reviews> productReviews, List<String> imageIds) {
		this.productCategory = productCategory;
		this.productId = productId;
		this.productName = productName;
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




	public List<Reviews> getProductReviews() {

		return productReviews;
	}





	public void setProductReviews(List<Reviews> productReviews) {

		this.productReviews = productReviews;
	}


	@Override
	public String toString() {
		return "Products{" +
				"productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", productCategory='" + productCategory + '\'' +
				", price=" + price +
				", productDesc='" + productDesc + '\'' +
				", productRating='" + productRating + '\'' +
				", brand='" + brand + '\'' +

				", productReviews=" + productReviews +
				", imageIds=" + imageIds +
				'}';
	}
}
