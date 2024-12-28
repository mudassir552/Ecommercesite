package com.ecommerceproject.products.dto;

import org.springframework.stereotype.Component;


public class PaymentResponseDto {

	  private String ClientSecret;
	  
	  private long Amount;
	  
	  private String Status;
	  
	  private String PaymentMethodType;

	public PaymentResponseDto(String clientSecret, long amount, String status, String paymentMethodType) {
	
		this.ClientSecret = clientSecret;
		this.Amount = amount;
		this.Status = status;
		this.PaymentMethodType = paymentMethodType;
	}

	public String getClientSecret() {
		return ClientSecret;
	}

	public void setClientSecret(String clientSecret) {
		ClientSecret = clientSecret;
	}

	public long getAmount() {
		return Amount;
	}

	public void setAmount(long amount) {
		Amount = amount;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPaymentMethodType() {
		return PaymentMethodType;
	}

	public void setPaymentMethodType(String paymentMethodType) {
		PaymentMethodType = paymentMethodType;
	}
	  
	  
}
