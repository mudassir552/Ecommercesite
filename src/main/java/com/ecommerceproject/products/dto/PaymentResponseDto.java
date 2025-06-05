package com.ecommerceproject.products.dto;

import org.springframework.stereotype.Component;


public class PaymentResponseDto {

	private String clientSecret;
	private Long amountInPaise;
	private String paymentIntentId;
	private String paymentMethod;

	public PaymentResponseDto(String clientSecret, Long amountInPaise, String paymentIntentId, String paymentMethod) {
		this.clientSecret = clientSecret;
		this.amountInPaise = amountInPaise;
		this.paymentIntentId = paymentIntentId;
		this.paymentMethod = paymentMethod;
	}


	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Long getAmountInPaise() {
		return amountInPaise;
	}

	public void setAmountInPaise(Long amountInPaise) {
		this.amountInPaise = amountInPaise;
	}

	public String getPaymentIntentId() {
		return paymentIntentId;
	}

	public void setPaymentIntentId(String paymentIntentId) {
		this.paymentIntentId = paymentIntentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
