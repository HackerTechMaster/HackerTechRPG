package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.objects.Player;

import static com.github.hackertechmaster.hackertechrpg.Launcher.gameScanner;

public abstract class BaseUI implements GameUserInterface {
    @Override
    public void start() {
        showGameInfo();
        showMenu();
        handleInput(gameScanner.readLine("请输入操作数").charAt(0));
    }

    public void handleHelp() {
        System.out.println("移动到不同的地区交易不同的物品来赚取节操~");
        System.out.println("[m] 地图  [i] 背包");
        System.out.println("[h] 帮助  [q] 退出");
        System.out.println("[b] 回到上一级菜单");
        start();
    }

    public void handleQuit() {
        String inputStr = gameScanner.readLine("退出游戏后所有进度都将丢失（暂无存档功能），确认退出请输入Y %n");
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
        Launcher.tickStop();
        EntryUI.INSTANCE.start();
    }

    public void handleDefault() {
        System.out.println("该选项不存在或在本菜单无法使用");
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
        System.out.println("=============================");
        Launcher.tick.showTimePassed();
        Player currentPlayer = Launcher.playerRegistry.getCurrentPlayer();
        if(currentPlayer != null) {
            String currentAreaName = currentPlayer.getArea().getAreaName();
            System.out.println(String.format("当前所在地区: %s", currentAreaName));
            currentPlayer.show();
        }
    }
}
