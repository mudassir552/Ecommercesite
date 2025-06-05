package com.ecommerceproject.services;

import java.util.List;

import com.ecommerceproject.products.ProductImages;

public interface ImageService {

	
	  public List<ProductImages> getPoductImagesById(String Id);
}
