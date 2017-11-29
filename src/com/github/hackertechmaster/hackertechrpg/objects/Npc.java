package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractEntity;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.IOrder;

import java.util.List;

public class Npc extends AbstractEntity {
    private boolean active;
    private List<IOrder> orders;

    public Npc(String name, Area area, List<IOrder> orders) {
        this(name, area, true, orders);
    }

    public Npc(String name, Area area, boolean active, List<IOrder> orders) {
        super(name, area);
        this.active = active;
        this.orders = orders;
    }

    public boolean isActive() {
        return active;
    }

    public List<IOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<IOrder> orders) {
        this.orders = orders;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public AbstractInventory getInventory() {
        return null;
    }

    @Override
    public void show() {
        //
    }
}
