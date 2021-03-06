package com.github.hackertechmaster.hackertechrpg.ui;

public interface GameUserInterface {
    char MAP = 'm';
    char QUIT = 'q';
    char HELP = 'h';
    char INVENTORY = 'i';
    char BACK = 'b';
    int NUMBER_TO_CHAR_OFFSET = '0';

    /**
     * 显示菜单
     */
    void showMenu();

    /**
     * 处理输入事件
     * @param input 输入字符
     */
    void handleInput(char input);

    /**
     * 进入菜单
     */
    void start();

    /**
     * 展示玩家信息
     */
    void showGameInfo();
}
