package org.example.order;

import lombok.Builder;
import lombok.Getter;


@Getter
public class Electronics extends Product {
    private String manufacturer;

    @Builder
    public Electronics(String name, double price, String manufacturer) {
        super(name, price);
        this.manufacturer = manufacturer;
    }
}