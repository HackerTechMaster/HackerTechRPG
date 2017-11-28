package com.github.hackertechmaster.hackertechrpg.objects;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.interfaces.IPrintable;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

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

    private final AbstractPlayerRegistry playerManager;

    public GameMap(AbstractPlayerRegistry playerManager) {
        this.playerManager = playerManager;
    }

    public MoveResult movePlayer(AbstractPlayer player, Area toArea) {
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
