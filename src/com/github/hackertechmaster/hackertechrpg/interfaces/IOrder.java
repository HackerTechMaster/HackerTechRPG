package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface IOrder {
    enum BusinessType {
        BUYING("收购"),
        SELLING("出售");

        private String description;

        BusinessType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    String getItemName();
    int getPrice();
    BusinessType getBusinessType();
    int getAmount();
    void setAmount(int amount);
}
