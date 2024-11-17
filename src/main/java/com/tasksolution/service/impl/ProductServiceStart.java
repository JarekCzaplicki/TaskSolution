package com.tasksolution.service.impl;

import com.tasksolution.hellper.CreateProducts;
import com.tasksolution.model.Product;
import com.tasksolution.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("start")
public class ProductServiceStart implements ProductService {
    @Value("${locale}")
    private String locale;
    private Double summaryPrice;
    private final CreateProducts createProducts;
    private final MessageSource messageSource;

    public ProductServiceStart(CreateProducts createProducts, MessageSource messageSource) {
        this.createProducts = createProducts;
        this.messageSource = messageSource;
    }

    @Override
    public void printInfo() {
        List<Product> products = createProducts.create(5);
        for (Product product : products) {
            summaryPrice += product.getPrice();
        }
        System.out.println(messageSource.getMessage(
                "price_of_all_products"
                , new Object[]{summaryPrice}
                , Locale.forLanguageTag(this.locale)
        ));
    }
}
