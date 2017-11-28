package com.github.hackertechmaster.hackertechrpg.ui;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public final class MainUI extends BaseUI {
    public static MainUI INSTANCE = new MainUI();

    private MainUI() {}

    @Override
    public void showMenu() {
        println("=== 黑科技RPG V1.0 ===");
        //NPC List
    }

    @Override
    public void handleInput(char input) {
        final boolean npcFound = showCartByNpcChar(input);
        if(!npcFound) super.handleInput(input);
    }

    private boolean showCartByNpcChar(char npcChar) {
        return false;
    }
}
