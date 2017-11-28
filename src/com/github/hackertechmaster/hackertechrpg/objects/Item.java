package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

public class Item extends AbstractItem {


    public Item(String name, ItemType type, int stackCapacity, int stackAvailable) {
        super(name, type, stackCapacity, stackAvailable);
    }

    @Override
    public AbstractItem copy(int stackAvailable) {
        return new Item(getName(), getType(), stackAvailable, stackCapacity());
    }
}
