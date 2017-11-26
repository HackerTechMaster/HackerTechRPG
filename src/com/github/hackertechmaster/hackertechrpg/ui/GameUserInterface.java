package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public interface GameUserInterface {
    int MAP = 'm';
    int QUIT = 'q';
    int HELP = 'h';
    int INVENTORY = 'i';

    void showMenu();

    void handleInput(int input);

    default void start() {
        showPlayerInfo();
        showMenu();
        showCommonMenu();
        handleInput(Launcher.scanner.nextInt());
    }

    default void showCommonMenu() {
        println("[m] 地图  [i] 背包");
        println("[h] 帮助  [q] 退出");
    }

    default void showPlayerInfo() {
        Launcher.playerManager.getCurrentPlayer().show();
    }
}
