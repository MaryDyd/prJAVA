package org.example.order;

import org.example.order.Product;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class OrderRepository<T extends Product> {
    private final ConcurrentHashMap<UUID, T> orders = new ConcurrentHashMap<>();

    public void saveOrder(UUID orderId, T product) {
        orders.put(orderId, product);
    }

    public T getOrder(UUID orderId) {
        return orders.get(orderId);
    }
}
