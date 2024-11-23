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
@Profile("pro")
public class ProductServicePro implements ProductService {
    @Value("${discountRate}")
    private Double discountRate;
    @Value("${vatRate}")
    private Double vatRate;
    @Value("${locale}")
    private String locale;
    private Double summaryPrice = 0.0d;
    private Double summaryVatPrice = 0.0d;
    private Double summaryDiscountPrice = 0.0d;
    private final CreateProducts createProducts;
    private final MessageSource messageSource;

    public ProductServicePro(CreateProducts createProducts, MessageSource messageSource) {
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

        summaryVatPrice = Math.round((summaryPrice + (summaryPrice * vatRate)) * 100.0) / 100.0;

        System.out.println(messageSource.getMessage(
                "price_of_all_products_plus_vat"
                , new Object[]{summaryVatPrice}
                , Locale.forLanguageTag(this.locale)
        ));

        summaryDiscountPrice = Math.round((summaryPrice + (summaryPrice * discountRate)) * 100.0) / 100.0;

        System.out.println(messageSource.getMessage(
                "price_of_all_products_discount"
                , new Object[]{summaryDiscountPrice}
                , Locale.forLanguageTag(this.locale)
        ));
    }
}
