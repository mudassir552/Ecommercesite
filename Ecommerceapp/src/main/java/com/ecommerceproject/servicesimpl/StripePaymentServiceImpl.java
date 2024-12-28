package com.ecommerceproject.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerceproject.products.dto.PaymentResponseDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 @Service
 public class StripePaymentServiceImpl {
	
	   
	    private String stripeSecretKey="sk_test_51QQXn8K2Y1Mmm31OgRTlmGHbKNKEqYFuzJIbMoGC6OaSyb7E7eHt3bIAo6lYw4Rpogiv2YsMzD5H6MwnqHHpfBiO00pqlHTOQY";

	    public StripePaymentServiceImpl() {
	       
	        Stripe.apiKey = stripeSecretKey;
	    }

	    public PaymentResponseDto createPaymentIntent(long amount) throws StripeException {
	        // Amount is in cents, so multiply by 100 to avoid decimals
	        long amountInCents = amount * 100;

	        // Set up the parameters for the payment intent
	        Map<String, Object> params = new HashMap<>();
	        params.put("amount", amountInCents);
	        params.put("currency", "usd");
	        params.put("payment_method_types", java.util.List.of("card"));
	        PaymentIntent paymentIntent = PaymentIntent.create(params);
	        // Create the PaymentIntent on Stripe
	        PaymentResponseDto paymentResponseDto = new PaymentResponseDto(
	                paymentIntent.getClientSecret().toString(),
	                paymentIntent.getAmount(),
	                paymentIntent.getStatus(),
	                paymentIntent.getPaymentMethodTypes().toString() // Assuming you want to store payment method types
	            );
	        
	        System.out.println("SECRETTTT"+paymentIntent.getClientSecret());
	        return paymentResponseDto;

}
}