package com.geekbrains.webapp.springapp.models;

import com.geekbrains.webapp.springapp.dtos.OrderItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (OrderItemDto i : items) {
            if(i.getProductId().equals(productId)) {
                i.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()){
            OrderItemDto i = iter.next();
            if(i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }


    public void remove(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }
}
