package com.github.hackertechmaster.hackertechrpg.objects.items;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

public class Knife extends AbstractItem {
    public Knife() {
        this(1);
    }

    public Knife(int stackAvailable) {
        super("Knife", ItemType.TOOL, 1, stackAvailable);
    }
}
