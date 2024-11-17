package com.tasksolution.service;

import com.tasksolution.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceMain {
    private final ProductService productService;

    public ProductServiceMain(ProductService productService) {
        this.productService = productService;
        productService.printInfo();
    }
}
