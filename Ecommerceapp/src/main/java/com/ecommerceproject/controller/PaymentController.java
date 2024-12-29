package com.ecommerceproject.controller;

import com.stripe.model.PaymentIntent;
import com.ecommerceproject.products.dto.PaymentResponseDto;
import com.ecommerceproject.servicesimpl.StripePaymentServiceImpl;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripePaymentServiceImpl stripePaymentService;

    @GetMapping("/payments")
    public String showCheckoutPage(Model model) {
        // Add necessary attributes for the view (e.g., the total amount)
        model.addAttribute("amount", 5000); // Amount in cents ($50.00)
        return "payments"; 
    }

    @PostMapping("/create-payment-intent")
    @ResponseBody
    public PaymentResponseDto createPaymentIntent(@RequestParam long amount) {
        try {
            // Call the service to create a payment intent
            return stripePaymentService.createPaymentIntent(amount);
        } catch (StripeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating payment intent");
        }
    }
}
