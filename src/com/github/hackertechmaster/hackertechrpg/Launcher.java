package com.github.hackertechmaster.hackertechrpg;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.objects.GameMap;
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
import com.github.hackertechmaster.hackertechrpg.util.Tick;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Launcher {
    public static AbstractPlayerRegistry playerRegistry;
    public static ItemRegistry itemRegistry;
    public static NpcRegistry npcRegistry;
    public static GameMap gameMap;
    public static Tick tick;
    public static Scanner scanner;
    private static final ScheduledExecutorService executor;

    static {
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
        //显示程序主入口菜单（注册/登录）
        EntryUI.INSTANCE.start();
    }

    public static void tickStart() {
        //登陆后新开一个线程，定期执行handleTickEvent
        executor.scheduleAtFixedRate(() -> tick.handleTickEvent(), 0, 1, TimeUnit.SECONDS);
    }

    //FIXME not working properly
    public static void tickPause() {
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tickStop() {
        executor.shutdown();
    }

    public static void quit() {
        tickStop();
        System.exit(0);
    }
}
