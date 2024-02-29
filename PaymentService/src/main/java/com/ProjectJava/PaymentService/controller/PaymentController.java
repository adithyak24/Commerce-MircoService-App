package com.ProjectJava.PaymentService.controller;

import com.ProjectJava.PaymentService.model.PaymentRequest;
import com.ProjectJava.PaymentService.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        log.info("entered this control {}", paymentRequest);
        return new ResponseEntity<Long>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }
}
