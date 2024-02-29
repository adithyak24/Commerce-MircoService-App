package com.ProjectJava.PaymentService.service;

import com.ProjectJava.PaymentService.entity.TransactionDetails;
import com.ProjectJava.PaymentService.model.PaymentRequest;
import com.ProjectJava.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("recording payment details: {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder().paymentDate(Instant.now()).paymentMode(paymentRequest.getPaymentMode().name()).paymentStatus("SUCCESS").orderId(paymentRequest.getOrderId()).amount(paymentRequest.getAmount()).build();
        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());
        return 0;
    }
}
