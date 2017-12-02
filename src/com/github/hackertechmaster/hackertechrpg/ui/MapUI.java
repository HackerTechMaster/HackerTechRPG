package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.util.GameMap;

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
        System.out.println("=== 黑科技RPG V1.0 地图界面 ===");
        int index = 0;
        for(Area area : Area.values()) {
            char ch = (char) (index+NUMBER_TO_CHAR_OFFSET);
            System.out.println(String.format("[%c] %s (该地区有%d个NPC)",
                    ch, area.getAreaName(), Launcher.npcRegistry.getNpcsInArea(area).size()));
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
            System.out.println(moveResult.getDescription());
            if(moveResult == GameMap.MoveResult.OK) {
                handleBack();
            } else {
                start();
            }
        } else {
            super.handleInput(input);
        }
    }

    @Override
    public void handleMap() {
        System.out.println("你已经在地图界面啦~");
    }
}
