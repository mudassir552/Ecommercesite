package com.ecommerceproject.products;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductSpecification {
	
	  private String key;
	    private String value;

	   

	    public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

}
