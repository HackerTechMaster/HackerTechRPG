package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.util.Console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public final class MainUI extends BaseUI {
    private Map<Character, AbstractNpc> charToNpc = new HashMap<>();
    public static MainUI INSTANCE = new MainUI();

    private MainUI() {}

    @Override
    public void showMenu() {
        println("=== 黑科技RPG V1.0 主界面 ===");
        List<AbstractNpc> npcList = Launcher.npcRegistry.getNpcsInArea(Launcher.playerRegistry.getCurrentPlayer().getArea());
        if(npcList.size() > 0) {
            charToNpc = IntStream.range(0, npcList.size())
                    .boxed()
                    .collect(Collectors.toMap(i -> (char)(i+NUMBER_TO_CHAR_OFFSET), npcList::get));
            charToNpc.forEach((ch, npc) -> Console.println(String.format("[%c] %s", ch, npc.getName())));
        } else {
            println("当前地区没有NPC");
        }
    }

    @Override
    public void handleInput(char input) {
        final boolean npcFound = charToNpc.containsKey(input);
        if (npcFound) {
            AbstractNpc npc = charToNpc.get(input);
            CartUI.of(npc).start();
        } else {
            super.handleInput(input);
        }
    }
}
