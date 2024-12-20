package org.example.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public abstract class Product {
    private String name;
    private double price;

}
