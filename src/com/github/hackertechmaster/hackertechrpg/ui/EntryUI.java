package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

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
        println("=== 黑科技RPG V1.0 ===");
        println(String.format("[%c] 登录", LOGIN));
        println(String.format("[%c] 注册", REGISTER));
    }

    @Override
    public void handleHelp() {
        println("[q] 退出");
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
    public void handleInput(char input) {
        String inputStr;
        switch (input) {
            case LOGIN:
                println("请输入玩家名");
                inputStr = Launcher.scanner.nextLine();
                if(Launcher.playerRegistry.loginWithName(inputStr)) {
                    println(String.format("登录成功，玩家 %s", inputStr));
                    MainUI.INSTANCE.start();
                } else {
                    println("该玩家名不存在");
                    start();
                }
                break;
            case REGISTER:
                println("请输入玩家名");
                inputStr = Launcher.scanner.nextLine();
                if(Launcher.playerRegistry.register(inputStr)) {
                    println(String.format("注册成功，新增玩家 %s", inputStr));
                } else {
                    println("玩家名已存在，请重试");
                }
                start();
                break;
            default:
                super.handleInput(input);
                break;
        }
    }
}
