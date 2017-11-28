package com.github.hackertechmaster.hackertechrpg.objects.items;

import com.github.hackertechmaster.hackertechrpg.objects.Item;

public class Knife extends Item {
    public Knife() {
        this(1);
    }

    public Knife(int stackAvailable) {
        super("Knife", ItemType.TOOL, 1, stackAvailable);
    }
}
