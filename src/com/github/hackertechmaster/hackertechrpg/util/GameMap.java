package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.interfaces.IPrintable;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;
import com.github.hackertechmaster.hackertechrpg.objects.Player;
import com.github.hackertechmaster.hackertechrpg.registry.PlayerRegistry;

public class GameMap implements IPrintable {
    private static final int MOVE_ENERGY = 5;

    public enum MoveResult {
        OK("成功"),
        ENERGY_NOT_ENOUGH("体力不足"),
        ALREADY_THERE("你已经在那里啦");

        private String description;

        MoveResult(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private final PlayerRegistry playerManager;

    public GameMap(PlayerRegistry playerManager) {
        this.playerManager = playerManager;
    }

    public MoveResult movePlayer(Player player, Area toArea) {
        final int energyAvailable = player.getEnergyAvailable();
        if(energyAvailable >= MOVE_ENERGY) {
            final Area fromArea = player.getArea();
            if(fromArea != toArea) {
                player.setEnergyAvailable(energyAvailable - MOVE_ENERGY);
                player.setArea(toArea);
                return MoveResult.OK;
            } else {
                return MoveResult.ALREADY_THERE;
            }
        } else {
            return MoveResult.ENERGY_NOT_ENOUGH;
        }
    }

    public void moveNpc(Npc npc, Area toArea) {
        npc.setArea(toArea);
    }

    @Override
    public void show() {
        System.out.println("=== GameMap ===");
        for(Area area : Area.values()) {
            System.out.println(area.getAreaName());
        }

        Player currentPlayer = playerManager.getCurrentPlayer();
        if(currentPlayer != null) {
            String currentAreaName = currentPlayer.getArea().getAreaName();
            System.out.println(String.format("当前所在地区: %s", currentAreaName));
        }
    }
}