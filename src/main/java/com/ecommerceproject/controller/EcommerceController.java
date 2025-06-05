package com.ecommerceproject.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.ecommerceproject.products.dto.Reviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
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

import static java.util.stream.Collectors.toList;

@Controller
public class EcommerceController {
	
	@Autowired
	@Qualifier("productDescMongoTemplate")
	private MongoTemplate productDescMongoTemplate;
	
	@Autowired
  private EcommerceServiceImpl ecommerceserviceimpl;


	
	@Autowired
	private com.ecommerceproject.repository.Ecommercerepo Ecommercerepo;

	private static final Logger logger = LoggerFactory.getLogger(EcommerceController.class);
	
	 @GetMapping("/products/{category}")
	    public  String getProductsByCategory(@PathVariable("category") String category, Model model){
		 logger.info("hitting furnitures"+category);
		 List<ProductsDto> ProductsDto = ecommerceserviceimpl.findProductsByCategory(category);
       logger.info("prodfurni"+ProductsDto);
		 model.addAttribute("ProductsDto",ProductsDto);
	        return "productCards";
	    }
	
	 
	
	
	@GetMapping("/shopme") //shopme endpoint
	public String landingPage() {
		
		return "landingPage";
	}

	@GetMapping("/about")
	public String AboutPage() {

		return "about";
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
	public ResponseEntity<?> saveProductReviews(@PathVariable("id") String productId, @RequestBody Reviews reviews) {
		Query query = new Query(Criteria.where("productId").is(productId));
      logger.info("isNullll"+reviews.getProductReviews());
		Reviews newReview = new Reviews(reviews.getProductReviews(), LocalDateTime.now());


		Update update = new Update().push("productReviews", newReview);

		// Perform the update operation on the Products collection
		productDescMongoTemplate.updateFirst(query, update, Products.class);

		Map<String, String> responseMessage = new HashMap<>();
		responseMessage.put("message", "Review added successfully");

		return ResponseEntity.ok(responseMessage);
	}


	@GetMapping("/product/{id}/reviews/")
	public ResponseEntity<?> getProductReviews(@PathVariable("id") String productId) {
		Query query = new Query(Criteria.where("productId").is(productId));


		query.fields().include("productReviews"); // Only fetch productReviews field


		Products product = productDescMongoTemplate.findOne(query, Products.class); // Fetch Product, not Reviews
        logger.info("product"+product);

		if (product == null || product.getProductReviews() == null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
		}
		logger.info("product"+product.getProductReviews());

		List<Reviews> sortedReviews = product.getProductReviews().stream()
				.sorted(Comparator.comparing(Reviews::getDateposted).reversed()) // Sort reviews by datePosted in descending order
				.limit(6)  // Limit to the most recent 6 reviews
				.collect(Collectors.toList());
		logger.info("sorted"+sortedReviews);
		return ResponseEntity.ok(sortedReviews);
	}







}
