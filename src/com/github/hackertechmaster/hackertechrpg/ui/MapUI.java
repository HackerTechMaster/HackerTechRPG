package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.objects.Area;
import com.github.hackertechmaster.hackertechrpg.objects.GameMap;
import com.github.hackertechmaster.hackertechrpg.util.Console;

import java.util.HashMap;
import java.util.Map;

public class MapUI extends BaseUI {
    public static MapUI INSTANCE = new MapUI();
    private Map<Character, Area> charToArea = new HashMap<>();

    private MapUI() {
        //
    }

    @Override
    public void handleBack() {
        MainUI.INSTANCE.start();
    }

    @Override
    public void showMenu() {
        Console.println("=== 黑科技RPG V1.0 地图界面 ===");
        int index = 0;
        for(Area area : Area.values()) {
            char ch = (char) (index+NUMBER_TO_CHAR_OFFSET);
            Console.println(String.format("[%c] %s", ch, area.getAreaName()));
            charToArea.put(ch, area);
            index++;
        }
    }

    @Override
    public void handleInput(char input) {
        Area area = charToArea.get(input);
        final boolean areaFound = area != null;
        if(areaFound) {
            GameMap.MoveResult moveResult = Launcher.gameMap.movePlayer(Launcher.playerRegistry.getCurrentPlayer(), area);
            Console.println(moveResult.getDescription());
            start();
        } else {
            super.handleInput(input);
        }
    }

    @Override
    public void handleMap() {
        Console.println("你已经在地图界面啦~");
    }
}
