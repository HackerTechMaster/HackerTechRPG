package com.github.hackertechmaster.hackertechrpg;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerManager;
import com.github.hackertechmaster.hackertechrpg.util.Map;
import com.github.hackertechmaster.hackertechrpg.util.PlayerManager;
import com.github.hackertechmaster.hackertechrpg.util.Tick;

public class Launcher {
    public static void main(String[] args) {
        AbstractPlayerManager playerManager = new PlayerManager();
        Map map = new Map(playerManager);
        Tick tick = new Tick(playerManager);

        //新开一个线程，定期指定handleTickEvent
    }
}
