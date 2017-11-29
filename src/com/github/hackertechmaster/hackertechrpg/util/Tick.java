package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;

import java.util.concurrent.TimeUnit;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class Tick {
    //秒为单位
    private final long startTick;
    private long currentTick;
    private final AbstractPlayerRegistry playerManager;

    public Tick(AbstractPlayerRegistry playerManager) {
        this.startTick = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        this.currentTick = startTick;
        this.playerManager = playerManager;
    }

    public void handleTickEvent() {
        this.currentTick += TimeUnit.SECONDS.toSeconds(1);
        AbstractPlayer player = playerManager.getCurrentPlayer();
        //Restore energy
        player.setEnergyAvailable(player.getEnergyCapacity() + 1);
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
        long secondsPassed = currentTick-startTick;
        println(String.format("距游戏开始已经过去了%d秒", secondsPassed));
    }
}
