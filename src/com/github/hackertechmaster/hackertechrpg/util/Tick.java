package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerManager;

import java.util.concurrent.TimeUnit;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class Tick {
    //毫秒为单位
    private long startTime;
    private long currentTime;
    private AbstractPlayerManager playerManager;

    public Tick(AbstractPlayerManager playerManager) {
        this.startTime = System.currentTimeMillis();
        this.currentTime = startTime;
        this.playerManager = playerManager;
    };

    public void handleTickEvent() {
        this.currentTime = System.currentTimeMillis();
        AbstractPlayer player = playerManager.getCurrentPlayer();
        player.setEnergyAvailable(player.getEnergyCapacity() + 1);
        final int previousEnergy = player.getEnergyAvailable();
        if(previousEnergy < player.getEnergyCapacity()) {
            player.setEnergyAvailable(previousEnergy + 1);
        }
    }

    public void showTimePassed() {
        long secondsPassed = TimeUnit.MILLISECONDS.toSeconds(currentTime-startTime);
        println(String.format("距游戏开始已经过去了%d秒", secondsPassed));
    }
}
