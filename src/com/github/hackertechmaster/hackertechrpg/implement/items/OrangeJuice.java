package com.github.hackertechmaster.hackertechrpg.implement.items;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

public class OrangeJuice extends AbstractItem {
    public OrangeJuice() {
        this(1);
    }

    public OrangeJuice(int stackAvailable) {
        super("OrangeJuice", ItemType.DRINK, 3, stackAvailable);
    }
}
