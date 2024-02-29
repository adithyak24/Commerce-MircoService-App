package com.ProjectJava.OrderService.service;

import com.ProjectJava.OrderService.entity.Order;
import com.ProjectJava.OrderService.external.client.PaymentService;
import com.ProjectJava.OrderService.external.client.ProductService;
import com.ProjectJava.OrderService.external.request.PaymentRequest;
import com.ProjectJava.OrderService.model.OrderRequest;
import com.ProjectJava.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderClassImp implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Override
    public long placeOrder(OrderRequest orderRequest){
        log.info("placing order request {}",orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        Order order=Order.builder()
                .amount(orderRequest.getAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        log.info("checking order request {}",order);
        order = orderRepository.save(order);
        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getAmount())
                .build();
        log.info("checking payment generated {}",paymentRequest);
        String orderStatus = "temp";
        try{
            paymentService.doPayment(paymentRequest);
            log.info("Payment is successfully done");
            orderStatus="Placed";
        }
        catch(Exception e){
            log.error(e.toString());
            orderStatus="failed";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order Placed {}",order.getId());
        return order.getId();
    }
}
