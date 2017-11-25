package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.util.Console;

public abstract class AbstractEntity implements IEntity, IPrintable {
    @Override
    public void show() {
        Console.I.println("== No info ==");
    }
}
