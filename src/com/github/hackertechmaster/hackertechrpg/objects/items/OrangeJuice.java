package com.github.hackertechmaster.hackertechrpg.objects.items;

import com.github.hackertechmaster.hackertechrpg.objects.Item;

public class OrangeJuice extends Item {
    public OrangeJuice() {
        this(1);
    }

    public OrangeJuice(int stackAvailable) {
        super("OrangeJuice", ItemType.DRINK, 3, stackAvailable);
    }
}
