package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.util.Console;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public abstract class BaseUI implements GameUserInterface {
    @Override
    public void start() {
        showGameInfo();
        showMenu();
        handleInput(Launcher.scanner.next().charAt(0));
    }

    public void handleHelp() {
        println("移动到不同的地区交易不同的物品来赚取节操~");
        println("[m] 地图  [i] 背包");
        println("[h] 帮助  [q] 退出");
        println("[b] 回到上一级菜单");
        start();
    }

    public void handleQuit() {
        println("退出游戏后所有进度都将丢失（暂无存档功能），确认退出请输入Y");
        Console.skipRemainCharactersInSameLine();
        String inputStr = Launcher.scanner.nextLine();
        if(inputStr.equalsIgnoreCase("Y")) {
            Launcher.quit();
        } else {
            start();
        }
    }

    public void handleMap() {
        MapUI.INSTANCE.start();
    }

    public void handleInventory() {
        Launcher.playerRegistry.getCurrentPlayer().getInventory().show();
        start();
    }

    public void handleBack() {
        EntryUI.INSTANCE.start();
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
            case BACK:
                handleBack();
                break;
            default:
                handleDefault();
                break;
        }
    }

    @Override
    public void showGameInfo() {
        println("=============================");
        Launcher.tick.showTimePassed();
        AbstractPlayer currentPlayer = Launcher.playerRegistry.getCurrentPlayer();
        if(currentPlayer != null) {
            String currentAreaName = currentPlayer.getArea().getAreaName();
            println(String.format("当前所在地区: %s", currentAreaName));
            currentPlayer.show();
        }
    }
}
