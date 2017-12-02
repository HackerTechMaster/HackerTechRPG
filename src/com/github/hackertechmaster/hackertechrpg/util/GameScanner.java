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

    private String promptAndWaitForInput(String fmt, Object... args) {
        out.print(String.format(fmt, args));
        out.print(" # ");
        return scanner.nextLine();
    }

    public String readLine(String fmt, Object... args) {
        return Optional.ofNullable(System.console())
                .map(c -> c.readLine(fmt, args))
                .orElseGet(() -> promptAndWaitForInput(fmt, args));
    }
}
