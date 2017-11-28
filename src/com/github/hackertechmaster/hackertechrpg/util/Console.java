package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.Launcher;

public class Console {
    private Console() {}

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void newline() {
        System.out.println();
    }

    public static void skipRemainCharactersInSameLine() {
        Launcher.scanner.nextLine();
    }
}
