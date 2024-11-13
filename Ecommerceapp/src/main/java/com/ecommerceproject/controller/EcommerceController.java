package com.ecommerceproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;
import com.ecommerceproject.servicesimpl.EcommerceServiceImpl;

@Controller
public class EcommerceController {
	
	
	@Autowired()
	EcommerceServiceImpl ecommerceserviceimpl;
	
	

	
	
	 @GetMapping("/products/{category}")
	    public  String getProductsByCategory(@PathVariable("category") String category, Model model){
		 System.out.println("AAAAAAAAAAAA"+category);
		 List<ProductsDto> ProductsDto = ecommerceserviceimpl.findProductsByCategory(category);
	        model.addAttribute("ProductsDto",ProductsDto);
	        return "productCards";
	    }
	
	
	@GetMapping("/shopme")
	public String landingPage() {
		
		return "landingPage";
	}
	
	
	
	@GetMapping("/homepage")
	public String Homepage(Model model) {
		
		List<Products> products=ecommerceserviceimpl.getFirsttenproducts();
		
		
		
		model.addAttribute("Products", products);
		
		return "Products";
	}


	@GetMapping("/home")
	public String home() {
		return"Homepage";
	}
}
