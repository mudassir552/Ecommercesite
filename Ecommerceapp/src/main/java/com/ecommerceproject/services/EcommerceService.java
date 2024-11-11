package com.ecommerceproject.services;

import java.util.List;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;


public interface EcommerceService {

	
	List<Products> getFirsttenproducts();
	
	List<ProductsDto>findProductsByCategory(String category);
	 
}
