package org.example;


import org.example.order.OrderProcessor;
import org.example.order.Clothing;
import org.example.order.Electronics;
import org.example.order.Product;
import org.example.order.OrderRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Electronics laptop = new Electronics("Gaming Laptop", 1299.99, "ASUS ROG STRIX");
        Clothing tShirt = new Clothing("Cotton T-Shirt", 19.99, "L");

        System.out.println(laptop);
        System.out.println(tShirt);

        OrderProcessor<Electronics> electronicsProcessor = new OrderProcessor<>(laptop);
        OrderProcessor<Clothing> clothingProcessor = new OrderProcessor<>(tShirt);


        OrderRepository<Product> repository = new OrderRepository<>();

        UUID laptopOrderId = UUID.randomUUID();
        UUID tShirtOrderId = UUID.randomUUID();

        electronicsProcessor.processOrder(product -> {
            System.out.println("Processing electronics order: " + product.getName());
            repository.saveOrder(laptopOrderId, product);
        });

        clothingProcessor.processOrder(product -> {
            System.out.println("Processing clothing order: " + product.getName());
            repository.saveOrder(tShirtOrderId, product);
        });


        Thread.sleep(3000);

        System.out.println("Electronics order: " + repository.getOrder(laptopOrderId));
        System.out.println("Clothing order: " + repository.getOrder(tShirtOrderId));
    }
}