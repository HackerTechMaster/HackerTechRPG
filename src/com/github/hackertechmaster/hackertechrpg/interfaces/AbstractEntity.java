package com.github.hackertechmaster.hackertechrpg.interfaces;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public abstract class AbstractEntity implements IEntity, IPrintable {
    @Override
    public void show() {
        println("== No info ==");
    }
}
