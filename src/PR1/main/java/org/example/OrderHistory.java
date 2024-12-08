package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderHistory {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Історія замовлень:\n");
        orders.forEach(order -> sb.append(order).append("\n\n"));
        return sb.toString();
    }
}
