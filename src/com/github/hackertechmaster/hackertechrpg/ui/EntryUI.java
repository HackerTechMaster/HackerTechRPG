package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;

import static com.github.hackertechmaster.hackertechrpg.Launcher.gameScanner;

public final class EntryUI extends BaseUI {
    private static final char LOGIN = '0';
    private static final char REGISTER = '1';
    public static final EntryUI INSTANCE = new EntryUI();

    private EntryUI() {}

    @Override
    public void showGameInfo() {
        //Do nothing
    }

    @Override
    public void showMenu() {
        System.out.println("=== 黑科技RPG V1.0 初始界面 ===");
        System.out.println(String.format("[%c] 登录", LOGIN));
        System.out.println(String.format("[%c] 注册", REGISTER));
    }

    @Override
    public void handleHelp() {
        System.out.println("[q] 退出");
        start();
    }

    @Override
    public void handleMap() {
        handleDefault();
    }

    @Override
    public void handleInventory() {
        handleDefault();
    }

    @Override
    public void handleBack() {
        handleQuit();
    }

    @Override
    public void handleInput(char input) {
        String inputStr;
        switch (input) {
            case LOGIN:
                inputStr = gameScanner.readLine("请输入玩家名");
                if(Launcher.playerRegistry.loginWithName(inputStr)) {
                    System.out.println(String.format("登录成功，玩家 %s", inputStr));
                    Launcher.tickStart();
                    MainUI.INSTANCE.start();
                } else {
                    System.out.println("该玩家名不存在");
                    start();
                }
                break;
            case REGISTER:
                inputStr = gameScanner.readLine("请输入玩家名");
                if(Launcher.playerRegistry.register(inputStr)) {
                    System.out.println(String.format("注册成功，新增玩家 %s", inputStr));
                } else {
                    System.out.println("玩家名已存在，请重试");
                }
                start();
                break;
            default:
                super.handleInput(input);
                break;
        }
    }
}
