package com.ProjectJava.OrderService.service;

import com.ProjectJava.OrderService.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
