package com.seb.springboot.paymentservice.service;

import com.seb.springboot.paymentservice.dtos.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentServiceImpl implements PaymentService {

    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) {
        return null;
    }
}
