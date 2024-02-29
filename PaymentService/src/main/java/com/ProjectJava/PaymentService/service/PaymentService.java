package com.ProjectJava.PaymentService.service;

import com.ProjectJava.PaymentService.model.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
