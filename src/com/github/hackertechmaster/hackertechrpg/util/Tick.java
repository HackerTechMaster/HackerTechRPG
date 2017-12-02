package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.objects.Player;
import com.github.hackertechmaster.hackertechrpg.registry.PlayerRegistry;

import java.util.concurrent.TimeUnit;

public class Tick {
    //秒为单位
    private final long startTick;
    private long currentTick;
    private final PlayerRegistry playerManager;

    public Tick(PlayerRegistry playerManager) {
        this.startTick = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        this.currentTick = startTick;
        this.playerManager = playerManager;
    }

    public void handleTickEvent() {
        this.currentTick += 1;
        Player player = playerManager.getCurrentPlayer();
        final int previousEnergy = player.getEnergyAvailable();
        if(previousEnergy < player.getEnergyCapacity()) {
            player.setEnergyAvailable(previousEnergy + 1);
        }
        //Add money
        int moneyBefore = player.getMoney();
        if(moneyBefore < 300) {
            player.setMoney(moneyBefore+1);
        }
    }

    public void showTimePassed() {
        long ticksPassed = currentTick-startTick;
        System.out.println(String.format("距游戏开始已经过去了%d刻(1刻等于%d秒)", ticksPassed, Launcher.TICK_PER_SECONDS));
    }
}
