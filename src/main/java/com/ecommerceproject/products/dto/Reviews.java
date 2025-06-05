package com.ecommerceproject.products.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class Reviews {

    private String productReviews;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dateposted;
    public Reviews(){

    }

    public Reviews(String productReviews, LocalDateTime dateposted) {
        this.productReviews = productReviews;
        this.dateposted = dateposted;
    }



    public String getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(String productReviews) {
        this.productReviews = productReviews;
    }

    public LocalDateTime getDateposted() {
        return dateposted;
    }

    public void setDateposted(LocalDateTime dateposted) {
        this.dateposted = dateposted;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "productReviews='" + productReviews + '\'' +
                ", dateposted=" + dateposted +
                '}';
    }


}
