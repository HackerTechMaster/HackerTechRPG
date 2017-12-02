package com.github.hackertechmaster.hackertechrpg.util;

import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Will be used when there is no java.io.Console instance
 */
public class GameScanner {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    private String forgePrompt(String fmt, Object... args) {
        String promptSuffix = " # ";
        return String.format(fmt, args)+ promptSuffix;
    }

    private String showPromptAndWaitForInput(String promptStr) {
        out.print(promptStr);
        return scanner.nextLine();
    }

    public String readLine(String fmt, Object... args) {
        return Optional.ofNullable(System.console())
                .map(c -> c.readLine(forgePrompt(fmt, args)))
                .orElseGet(() -> showPromptAndWaitForInput(forgePrompt(fmt, args)));
    }
}
