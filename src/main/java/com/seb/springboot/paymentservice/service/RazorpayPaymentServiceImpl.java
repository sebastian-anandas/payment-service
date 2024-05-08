package com.seb.springboot.paymentservice.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.seb.springboot.paymentservice.dtos.PaymentResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorpayPaymentServiceImpl implements PaymentService {

    private RazorpayClient razorpayClient;

    public RazorpayPaymentServiceImpl(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String doPayment(String email, String phoneNumber, Long amount, String orderId) throws RazorpayException {

        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("receipt", orderId);

            JSONObject customerDetails = new JSONObject();
            customerDetails.put("email", email);
            customerDetails.put("contact", phoneNumber);

            paymentLinkRequest.put("customer", customerDetails);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "https://domain.com/razorpayWebHook");
            paymentLinkRequest.put("callback_method", "post");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
            System.out.println(paymentLink);
            return paymentLink.toString();

        } catch (Exception e) {
            e.printStackTrace();
//            throw new RazorpayException("Error creating payment link", e);
        }
        return null;
    }
}
