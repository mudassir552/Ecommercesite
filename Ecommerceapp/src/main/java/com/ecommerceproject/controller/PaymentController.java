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
       
        model.addAttribute("amount", 5000); 
        return "payments"; 
    }

    @PostMapping("/create-payment-intent")
    @ResponseBody
    public PaymentResponseDto createPaymentIntent(@RequestParam long amount) {
        try {
            
            return stripePaymentService.createPaymentIntent(amount);
        } catch (StripeException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating payment intent");
        }
    }
}
