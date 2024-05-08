package com.seb.springboot.paymentservice.service;

import com.razorpay.RazorpayException;
import com.seb.springboot.paymentservice.dtos.PaymentResponse;

public interface PaymentService {
    String doPayment(String email, String phoneNumber, Long amount, String orderId) throws RazorpayException;
}
