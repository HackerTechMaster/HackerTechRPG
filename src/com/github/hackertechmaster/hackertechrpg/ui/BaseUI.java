package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public abstract class BaseUI implements GameUserInterface {
    @Override
    public void start() {
        showPlayerInfo();
        showMenu();
        handleInput(Launcher.scanner.next().charAt(0));
    }

    public void handleHelp() {
        println("[m] 地图  [i] 背包");
        println("[h] 帮助  [q] 退出");
    }

    public void handleQuit() {
        println("退出游戏后所有进度都将丢失（暂无存档功能），确认退出请输入Y");
        String inputStr = Launcher.scanner.nextLine();
        if(inputStr.equalsIgnoreCase("Y")) {
            Launcher.quit();
        } else {
            start();
        }
    }

    public void handleMap() {
        Launcher.gameMap.show();
    }

    public void handleInventory() {
        Launcher.playerRegistry.getCurrentPlayer().getInventory().show();
    }

    public void handleDefault() {
        println("该选项不存在或在本菜单无法使用");
        start();
    }

    @Override
    public void handleInput(char input) {
        switch (input) {
            case QUIT:
                handleQuit();
                break;
            case HELP:
                handleHelp();
                break;
            case MAP:
                handleMap();
                break;
            case INVENTORY:
                handleInventory();
                break;
            default:
                handleDefault();
                break;
        }
    }

    @Override
    public void showPlayerInfo() {
        Launcher.playerRegistry.getCurrentPlayer().show();
    }
}
