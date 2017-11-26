package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public final class EntryUI implements GameUserInterface {
    private static final int LOGIN = 0;
    private static final int REGISTER = 1;
    public static final EntryUI INSTANCE = new EntryUI();

    private EntryUI() {}

    @Override
    public void showPlayerInfo() {
        //Do nothing
    }

    @Override
    public void showMenu() {
        println("=== 黑科技RPG V1.0 ===");
        println(String.format("[%d] 登录", LOGIN));
        println(String.format("[%d] 注册", REGISTER));
    }

    @Override
    public void showCommonMenu() {
        println("[q] 退出");
    }

    @Override
    public void handleInput(int input) {
        String inputStr;
        switch (input) {
            case LOGIN:
                println("请输入玩家名");
                inputStr = Launcher.scanner.nextLine();
                if(Launcher.playerManager.loginWithName(inputStr)) {
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
                if(Launcher.playerManager.register(inputStr)) {
                    println(String.format("注册成功，新增玩家 %s", inputStr));
                } else {
                    println("玩家名已存在，请重试");
                }
                start();
                break;
            case QUIT:
                println("退出游戏后所有进度都将丢失（暂无存档功能），确认退出请输入Y");
                inputStr = Launcher.scanner.nextLine();
                if(inputStr.equalsIgnoreCase("Y")) {
                    Launcher.quit();
                } else {
                    start();
                }
                break;
            default:
                println("不存在的选项");
                start();
                break;
        }
    }
}
