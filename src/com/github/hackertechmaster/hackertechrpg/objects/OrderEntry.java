package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry;

public class OrderEntry implements IOrderEntry {
    private final String itemName;
    private final int price;
    private final BusinessType businessType;
    private int amount;

    public OrderEntry(String itemName, int price, BusinessType businessType, int amount) {
        this.itemName = itemName;
        this.price = price;
        this.businessType = businessType;
        this.amount = amount;
    }

    public static class Builder {
        private String itemName;
        private int price;
        private int amount;

        public Builder item(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public OrderEntry sell() {
            return new OrderEntry(itemName, price, BusinessType.SELLING, amount);
        }

        public OrderEntry buy() {
            return new OrderEntry(itemName, price, BusinessType.BUYING, amount);
        }
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
