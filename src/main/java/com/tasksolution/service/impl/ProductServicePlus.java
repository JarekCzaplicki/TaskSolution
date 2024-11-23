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
@Profile("plus")
public class ProductServicePlus extends ProductServiceStart {
    @Value("${vatRate}")
    private double vatRate;


    public ProductServicePlus(CreateProducts createProducts, MessageSource messageSource) {
        super(createProducts, messageSource);
    }

    @Override
    public void printInfo() {
        super.printInfo(); // text from ProductServiceStart

        System.out.println(messageSource.getMessage(
                "price_of_all_products_plus_vat"
                , new Object[]{Math.round((summaryPrice + (summaryPrice * vatRate)) * 100.0) / 100.0}
                , Locale.forLanguageTag(this.locale)
        ));
    }
}
