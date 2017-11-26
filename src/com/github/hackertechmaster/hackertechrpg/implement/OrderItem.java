package com.github.hackertechmaster.hackertechrpg.implement;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractOrderItem;

public class OrderItem extends AbstractOrderItem {
    public OrderItem(String name, ItemType type, int stackCapacity, int stackAvailable, int price) {
        super(name, type, stackCapacity, stackAvailable, price);
    }

    public static OrderItem wrapItem(AbstractItem item, int price) {
        return new OrderItem(item.getName(), item.getType(), item.stackCapacity(), item.stackAvailable(), price);
    }
}
