package com.ProjectJava.ProductService.service;

import com.ProjectJava.ProductService.model.ProductRequest;
import com.ProjectJava.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest pr);

    ProductResponse getProduct(long id);

    void reduceQuantity(long productId, long quantity);
}
