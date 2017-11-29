package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;
import com.github.hackertechmaster.hackertechrpg.util.Console;

public class Item extends AbstractItem {


    public Item(String name, ItemType type, int stackCapacity, int stackAvailable) {
        super(name, type, stackCapacity, stackAvailable);
    }

    @Override
    public AbstractItem copy(int stackAvailable) {
        return new Item(getName(), getType(), stackCapacity(), stackAvailable);
    }

    @Override
    public void show() {
        Console.print(String.format(" 名称：%s | 类型：%s | %d/%d", getName(), getType(), stackAvailable(), stackCapacity()));
    }
}
