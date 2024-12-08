package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private final List<Product> products;
    private final double totalPrice;
    private String status;

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
    }
}
