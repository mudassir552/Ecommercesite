package com.ecommerceproject.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.products.ProductImages;
import com.ecommerceproject.products.ProductImages;
import com.ecommerceproject.products.Products;

@Repository
public interface ImageRepository extends MongoRepository<ProductImages,String>{

	
	ProductImages findByProductId(String productId);
}
