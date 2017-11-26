package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractOrderItem;

import java.util.Map;

public class Npc extends AbstractNpc {
    private int money;
    private boolean active;
    private Map<AbstractOrderItem, Integer> shopMap;

    public Npc(String name, Area area, int money, boolean active, Map<AbstractOrderItem, Integer> shopMap) {
        super(name, area);
        this.money = money;
        this.active = active;
        this.shopMap = shopMap;
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
    public Map<AbstractOrderItem, Integer> getShopMap() {
        return shopMap;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void setShopMap(Map<AbstractOrderItem, Integer> shopMap) {
        this.shopMap = shopMap;
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
