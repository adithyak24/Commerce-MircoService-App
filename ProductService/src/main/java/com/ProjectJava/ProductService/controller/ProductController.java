package com.ProjectJava.ProductService.controller;

import com.ProjectJava.ProductService.model.ProductRequest;
import com.ProjectJava.ProductService.model.ProductResponse;
import com.ProjectJava.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String testProduct(){
        return "Hello";
    }
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest pr){
        long productId = productService.addProduct(pr);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public  ResponseEntity<ProductResponse> getProductFromDB(@PathVariable("id") long Id){
        ProductResponse productResponse = productService.getProduct(Id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @PutMapping("reduceQuantity/{id}/")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity) {
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
