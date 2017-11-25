package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.util.Area;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public abstract class AbstractEntity implements IEntity, IPrintable {
    private Area area;

    public AbstractEntity(Area area) {
        this.area = area;
    }

    @Override
    public void show() {
        println("== No info ==");
    }
}
