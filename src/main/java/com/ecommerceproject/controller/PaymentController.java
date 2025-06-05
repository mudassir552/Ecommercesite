package com.ecommerceproject.controller;

import com.ecommerceproject.products.dto.PaymentRequest;
import com.ecommerceproject.products.dto.PaymentResponseDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@Controller
public class PaymentController {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.webhook.secret}")
    private String WebSecretKey;

    @Value("${stripe.public.key}")
    private String publicKey;


    @GetMapping("/payment-success")
    public String showPaymentSuccessPage() {
        return "/payment-success";  // This will render payment-success.html using Thymeleaf
    }
    @GetMapping ("/payments")
  public String payments(Model model, HttpServletRequest request){
        model.addAttribute("stripePublicKey", publicKey);


        String returnUrl = String.format("%s://%s:%d/payment-success",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        model.addAttribute("returnUrl", returnUrl);

        return "payments";

    }

    @PostMapping("/create-payment-intent")
    @ResponseBody
    public PaymentResponseDto createPaymentIntent(@RequestBody PaymentRequest request) {
        try {
            Stripe.apiKey = stripeSecretKey;

            // Convert amount to paise
            Long amountInPaise = request.getAmount() * 100L;

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInPaise)
                    .setCurrency("inr")
                    .setDescription("Payment for order #" + request.getOrderId())
                    .putMetadata("order_id", request.getOrderId())
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return new PaymentResponseDto(
                    paymentIntent.getClientSecret(),
                    amountInPaise,
                    paymentIntent.getId(),
                    paymentIntent.getPaymentMethod()
            );
        } catch (StripeException e) {
            throw new RuntimeException("Error creating payment intent", e);
        }
    }

    @PostMapping("/payment/status")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
                                                      @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            Event event = Webhook.constructEvent(
                    payload,
                    sigHeader,
                    WebSecretKey
            );

            // Get the type of event
            String eventType = event.getType();

            PaymentIntent paymentIntent = event.getDataObjectDeserializer()
                    .getObject()
                    .map(PaymentIntent.class::cast)
                    .orElse(null);

            switch (eventType) {
                case "payment_intent.succeeded":
                    handleSuccessfulPayment(paymentIntent);
                    break;
                case "payment_intent.payment_failed":
                    handleFailedPayment(paymentIntent);
                    break;
            }

            return ResponseEntity.ok().body("Webhook processed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook error: " + e.getMessage());
        }
    }

    private void handleSuccessfulPayment(PaymentIntent paymentIntent) {
        // Get the order ID from metadata
        String orderId = paymentIntent.getMetadata().get("order_id");
        // Update your database - payment successful
        System.out.println("Payment succeeded for order: " + orderId);
    }

    private void handleFailedPayment(PaymentIntent paymentIntent) {
        // Get the order ID from metadata
        String orderId = paymentIntent.getMetadata().get("order_id");
        // Update your database - payment failed
        System.out.println("Payment failed for order: " + orderId);
    }
}
