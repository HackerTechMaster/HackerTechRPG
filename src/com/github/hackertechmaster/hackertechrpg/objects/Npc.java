package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry;

import java.util.List;

public class Npc extends AbstractNpc {
    private boolean active;
    private List<IOrderEntry> orders;

    public Npc(String name, Area area, List<IOrderEntry> orders) {
        this(name, area, true, orders);
    }

    public Npc(String name, Area area, boolean active, List<IOrderEntry> orders) {
        super(name, area);
        this.active = active;
        this.orders = orders;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public List<IOrderEntry> getOrders() {
        return orders;
    }

    public void setOrders(List<IOrderEntry> orders) {
        this.orders = orders;
    }

    @Override
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
