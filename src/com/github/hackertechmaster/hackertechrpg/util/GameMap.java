package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.interfaces.IPrintable;
import com.github.hackertechmaster.hackertechrpg.objects.Area;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class GameMap implements IPrintable {
    private static final int MOVE_ENERGY = 5;
    private static final int OK = 0;
    private static final int ENERGY_NOT_ENOUGH = 1;
    private static final int ALREADY_THERE = 2;

    private final AbstractPlayerRegistry playerManager;

    public GameMap(AbstractPlayerRegistry playerManager) {
        this.playerManager = playerManager;
    }

    public int movePlayer(AbstractPlayer player, Area toArea) {
        final int energyAvailable = player.getEnergyAvailable();
        if(energyAvailable >= MOVE_ENERGY) {
            final Area fromArea = player.getArea();
            if(fromArea != toArea) {
                player.setEnergyAvailable(energyAvailable - MOVE_ENERGY);
                player.setArea(toArea);
                return OK;
            } else {
                return ALREADY_THERE;
            }
        } else {
            return ENERGY_NOT_ENOUGH;
        }
    }

    public void moveNpc(AbstractNpc npc, Area toArea) {
        npc.setArea(toArea);
    }

    @Override
    public void show() {
        println("=== GameMap ===");
        for(Area area : Area.values()) {
            println(area.getAreaName());
        }

        AbstractPlayer currentPlayer = playerManager.getCurrentPlayer();
        if(currentPlayer != null) {
            String currentAreaName = currentPlayer.getArea().getAreaName();
            println(String.format("当前所在地区: %s", currentAreaName));
        }
    }
}
