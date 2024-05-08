package com.seb.springboot.paymentservice.controller;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.seb.springboot.paymentservice.dtos.InitiatePaymentRequestDto;
import com.seb.springboot.paymentservice.dtos.PaymentResponse;
import com.seb.springboot.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService razorpayPaymentService;
    private PaymentService stripePaymentService;

    @Autowired
    public PaymentController(
            @Qualifier("razorpay") PaymentService theRazorpayPaymentService,
            @Qualifier("stripe") PaymentService theStripePaymentService
    ) {
        this.razorpayPaymentService = theRazorpayPaymentService;
        this.stripePaymentService = theStripePaymentService;
    }

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException {

        // switch payment service between razorpay and stripe service
        int paymentGatewayOption = choosePaymentGateway();

        switch(paymentGatewayOption) {
            case 1 : return razorpayPaymentService.doPayment(
                    requestDto.getEmail(),
                    requestDto.getPhoneNumber(),
                    requestDto.getAmount(),
                    requestDto.getOrderId()
            );
            case 2 : return stripePaymentService.doPayment(
                    requestDto.getEmail(),
                    requestDto.getPhoneNumber(),
                    requestDto.getAmount(),
                    requestDto.getOrderId()
            );
        }
        return null;
    }

    public int choosePaymentGateway() {
        // add payment gateway option code
        return 1;
    }

}
