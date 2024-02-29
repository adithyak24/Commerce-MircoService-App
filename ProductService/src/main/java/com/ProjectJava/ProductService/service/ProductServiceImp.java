package com.ProjectJava.ProductService.service;

import com.ProjectJava.ProductService.entity.Product;
import com.ProjectJava.ProductService.model.ProductRequest;
import com.ProjectJava.ProductService.model.ProductResponse;
import com.ProjectJava.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest pr) {
        log.info("Adding Product");
        Product product = Product.builder()
                .productName(pr.getName())
                .quantity(pr.getQuantity())
                .price(pr.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProduct(long id) {
        Product product= productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for ID {}",productId, quantity);
        Product product=productRepository.findById(productId).orElseThrow();

        if(product.getProductId()<quantity){
            log.info("product doesnot have quantity");
        }
        else {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
            log.info("Product Quantity updated Successfully");
        }
    }
}
