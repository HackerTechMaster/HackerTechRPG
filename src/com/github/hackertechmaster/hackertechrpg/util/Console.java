package com.github.hackertechmaster.hackertechrpg.util;

public class Console {
    public static Console I = new Console();

    private Console() {}

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }
}
