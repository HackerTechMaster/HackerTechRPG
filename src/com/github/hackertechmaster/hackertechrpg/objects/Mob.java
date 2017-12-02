package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractEntity;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.IDamageable;

public class Mob extends AbstractEntity implements IDamageable {
    private int health;
    private final int healthLimit;

    public Mob(String name, Area area, int health, int healthLimit) {
        super(name, area);
        this.health = health;
        this.healthLimit = healthLimit;
    }

    @Override
    public AbstractInventory getInventory() {
        return null;
    }

    @Override
    public void show() {
        System.out.println(String.format("怪物名：%s | HP： %d/%d",
                getName(), getHealth(), getHealthLimit()));
    }

    @Override
    public int getHealthLimit() {
        return healthLimit;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
