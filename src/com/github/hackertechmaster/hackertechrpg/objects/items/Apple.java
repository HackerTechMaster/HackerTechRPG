package com.github.hackertechmaster.hackertechrpg.objects.items;

import com.github.hackertechmaster.hackertechrpg.objects.Item;

public class Apple extends Item {
    public Apple() {
        this(1);
    }

    public Apple(int stackAvailable) {
        super("Apple", ItemType.FRUIT, 16, stackAvailable);
    }
}
