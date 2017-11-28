package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry;

import java.util.List;

public class Npc extends AbstractNpc {
    private int money;
    private boolean active;
    private List<IOrderEntry> orders;

    public Npc(String name, Area area, int money, boolean active, List<IOrderEntry> orders) {
        super(name, area);
        this.money = money;
        this.active = active;
        this.orders = orders;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int cnt) {
        this.money = cnt;
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
