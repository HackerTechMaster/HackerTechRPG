package com.github.hackertechmaster.hackertechrpg.implement.items;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

public class Apple extends AbstractItem {
    public Apple() {
        this(1);
    }

    public Apple(int stackAvailable) {
        super("Apple", ItemType.FRUIT, 16, stackAvailable);
    }
}
