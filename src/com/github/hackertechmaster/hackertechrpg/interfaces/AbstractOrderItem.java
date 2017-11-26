package com.github.hackertechmaster.hackertechrpg.interfaces;

public class AbstractOrderItem extends AbstractItem {
    private int price;

    public AbstractOrderItem(String name, ItemType type, int stackCapacity, int stackAvailable, int price) {
        super(name, type, stackCapacity, stackAvailable);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
