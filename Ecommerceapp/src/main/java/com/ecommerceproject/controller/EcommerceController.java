package com.ecommerceproject.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerceproject.products.Products;
import com.ecommerceproject.products.dto.ProductsDto;
import com.ecommerceproject.servicesimpl.EcommerceServiceImpl;

@Controller
public class EcommerceController {
	
	@Autowired
	@Qualifier("productDescMongoTemplate")
	private MongoTemplate productDescMongoTemplate;
	
	@Autowired
  private EcommerceServiceImpl ecommerceserviceimpl;
	
	
	@Autowired
	private com.ecommerceproject.repository.Ecommercerepo Ecommercerepo;

	
	
	 @GetMapping("/products/{category}")
	    public  String getProductsByCategory(@PathVariable("category") String category, Model model){
		 
		 List<ProductsDto> ProductsDto = ecommerceserviceimpl.findProductsByCategory(category);
		 
		 ProductsDto.forEach(prod -> System.out.println("CONTROLLER"+prod.getProductId()));
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
	
	@GetMapping("/checkout")
	public String checkout() {
		return "checkout";
	}
	

	@PostMapping("/submit-rating")
	@ResponseBody
	public ResponseEntity<?> saveProductRating(@RequestBody Products productdto) throws Exception {
	    try {
	        if (ecommerceserviceimpl.saveProductRating(productdto) != null) {
	           
	            return ResponseEntity.ok().body(Collections.singletonMap("message", "Product Rating updated successfully."));
	        } else {
	            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Failed to update product rating."));
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body(Collections.singletonMap("message", "Error while updating product rating: " + e.getMessage()));
	    }
	}
	
	@PostMapping("/product/{id}/reviews/")
public ResponseEntity<?> saveProductReviews(@PathVariable("id")String productId,@RequestBody List<String> productReviews){
		
		
		   Query query = new Query(Criteria.where("productId").is(productId));
		 Update update = new Update().push("productReviews", productReviews);
		 productDescMongoTemplate.updateFirst(query, update, Products.class);
		 return ResponseEntity.ok("Reviews saved successfully");
		
	}
	
	@GetMapping("/product/{id}/reviews/")
	public ResponseEntity<?> getProductReviews(@PathVariable("id")String productId){
			
			
			   Query query = new Query(Criteria.where("productId").is(productId));
			   Products product = productDescMongoTemplate.findOne(query, Products.class);

			   
			    if (product.getProductReviews()==null) {
			    	System.out.println("null"+product);
			    	 return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
			    }

		
			    List<String> reviews = product.getProductReviews(); 

			   
			    return ResponseEntity.ok(reviews);
			
		}
	
}
