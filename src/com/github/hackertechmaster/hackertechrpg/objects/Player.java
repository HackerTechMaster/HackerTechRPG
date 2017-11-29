package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractEntity;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.util.Console;

public class Player extends AbstractEntity {
    private static final int ENERGY_CAPACITY = 20;

    private int money;
    private int energyAvailable;
    private int energyCapacity;
    private AbstractInventory inventory;

    public Player(String name, Area area, AbstractInventory inventory) {
        super(name, area);
        this.money = 100;
        this.energyAvailable = ENERGY_CAPACITY;
        this.energyCapacity = ENERGY_CAPACITY;
        this.inventory = inventory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int cnt) {
        this.money = cnt;
    }

    public int getEnergyAvailable() {
        return energyAvailable;
    }

    public void setEnergyAvailable(int cnt) {
        this.energyAvailable = cnt>energyCapacity ? energyCapacity : cnt;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    @Override
    public AbstractInventory getInventory() {
        return inventory;
    }

    @Override
    public void show() {
        Console.println(String.format("玩家名：%s | 体力： %d/%d | 节操： %d",
                getName(), getEnergyAvailable(), getEnergyCapacity(), getMoney()));
    }
}
