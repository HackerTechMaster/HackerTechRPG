package com.github.hackertechmaster.hackertechrpg;

import com.github.hackertechmaster.hackertechrpg.objects.items.Apple;
import com.github.hackertechmaster.hackertechrpg.objects.items.Knife;
import com.github.hackertechmaster.hackertechrpg.objects.items.OrangeJuice;
import com.github.hackertechmaster.hackertechrpg.objects.npcs.Liaolun;
import com.github.hackertechmaster.hackertechrpg.objects.npcs.Tony;
import com.github.hackertechmaster.hackertechrpg.objects.npcs.Xiaosan;
import com.github.hackertechmaster.hackertechrpg.registry.ItemRegistry;
import com.github.hackertechmaster.hackertechrpg.registry.NpcRegistry;
import com.github.hackertechmaster.hackertechrpg.registry.PlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.ui.EntryUI;
import com.github.hackertechmaster.hackertechrpg.util.GameMap;
import com.github.hackertechmaster.hackertechrpg.util.GameScanner;
import com.github.hackertechmaster.hackertechrpg.util.Tick;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static final long TICK_PER_SECONDS = 3;

    public static PlayerRegistry playerRegistry;
    public static ItemRegistry itemRegistry;
    public static NpcRegistry npcRegistry;
    public static GameMap gameMap;
    public static Tick tick;
    public static Scanner scanner;
    private static ScheduledExecutorService executor;
    public static GameScanner gameScanner;

    static {
        gameScanner = new GameScanner();
        playerRegistry = new PlayerRegistry();
        itemRegistry = new ItemRegistry();
        npcRegistry = new NpcRegistry();
        gameMap = new GameMap(playerRegistry);
        tick = new Tick(playerRegistry);
        scanner = new Scanner(System.in);
        executor = Executors.newScheduledThreadPool(1);

        itemRegistry.register(new Apple());
        itemRegistry.register(new Knife());
        itemRegistry.register(new OrangeJuice());
        npcRegistry.register(new Tony());
        npcRegistry.register(new Liaolun());
        npcRegistry.register(new Xiaosan());
    }

    public static void main(String[] args) {
        EntryUI.INSTANCE.start();
    }

    public static void tickStart() {
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> tick.handleTickEvent(), 0, TICK_PER_SECONDS, TimeUnit.SECONDS);
    }

    public static void tickStop() {
        executor.shutdown();
    }

    public static void quit() {
        tickStop();
        System.exit(0);
    }
}
