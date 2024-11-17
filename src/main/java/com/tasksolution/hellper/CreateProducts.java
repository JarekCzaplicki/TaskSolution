package com.tasksolution.hellper;

import com.tasksolution.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateProducts {
    private final double MIN_PRICE = 50;
    private final double MAX_PRICE = 500;
    List<Product> listOfProducts =new ArrayList<>();

    public List<Product> create(int numberOfProducts){
        for (int i = 1; i <= numberOfProducts; i++) {
            listOfProducts.add(new Product("Product_" + i, generatePrice()));
        }
        return listOfProducts;
    }

    private double generatePrice() {
        return Math.random() * (MAX_PRICE - MIN_PRICE) + MIN_PRICE;
    }
}
