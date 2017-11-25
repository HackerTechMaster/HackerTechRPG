package com.github.hackertechmaster.hackertechrpg.implement;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.util.Area;

public class Player extends AbstractPlayer {
    private static int ENERGY_CAPACITY = 20;

    private String name;
    private int money;
    private int energyAvailable;
    private int energyCapacity;
    private AbstractInventory inventory;

    public Player(Area area, String name, AbstractInventory inventory) {
        super(area);
        this.name = name;
        this.money = 0;
        this.energyAvailable = 20;
        this.energyCapacity = ENERGY_CAPACITY;
        this.inventory = inventory;
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
    public int getEnergyAvailable() {
        return energyAvailable;
    }

    @Override
    public void setEnergyAvailable(int cnt) {
        this.energyAvailable = cnt>energyCapacity ? energyCapacity : cnt;
    }

    @Override
    public int getEnergyCapacity() {
        return energyCapacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractInventory getInventory() {
        return inventory;
    }
}
