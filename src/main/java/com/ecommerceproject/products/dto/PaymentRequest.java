package com.ecommerceproject.products.dto;

public class PaymentRequest {
    private Long amount;    // Amount in INR (not paise)
    private String orderId;

    // Getters and setters
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
