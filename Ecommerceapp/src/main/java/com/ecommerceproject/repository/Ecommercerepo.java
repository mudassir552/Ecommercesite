package com.ecommerceproject.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;

@Repository
public interface Ecommercerepo extends MongoRepository<Products,String> {

	
	
	List<ProductsDto> findByproductCategory(String category);

	String findByProductId(String productid);
}
