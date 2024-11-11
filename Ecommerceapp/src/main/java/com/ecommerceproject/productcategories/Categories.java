package com.ecommerceproject.productcategories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ecommerceproject.products.Products;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


public class Categories {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long categroy_id;
	    
	 
	    

	    @OneToMany(mappedBy = "product_id")
	    private List<Products> products = new ArrayList<>();

}
