package com.ecommerceproject.products.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;



public class ProductsDto implements Serializable  {
	 @Field("productId") 
	private String productId;
	 private String productName;
	    private String productCategory;
	    private double price;
	    
	    private String productDesc;
	    private String productRating;
	    private String brand;
	    
	    private String imageId; 
	    private String imageUrl;
	    
	    





		public ProductsDto(String productId, String productName, String productCategory, double price,
				String productDesc, String productRating, String brand, String imageId, String imageUrl) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.productCategory = productCategory;
			this.price = price;
			this.productDesc = productDesc;
			this.productRating = productRating;
			this.brand = brand;
			this.imageId = imageId;
			this.imageUrl = imageUrl;
		}






		public ProductsDto() {
			// TODO Auto-generated constructor stub
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











		public String getImageId() {
			return imageId;
		}






		public void setImageId(String imageId) {
			this.imageId = imageId;
		}






		public String getImageUrl() {
			return imageUrl;
		}






		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	    
	    
	    
	    
	    

}
