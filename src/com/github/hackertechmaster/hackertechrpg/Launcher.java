package com.github.hackertechmaster.hackertechrpg;

import com.github.hackertechmaster.hackertechrpg.objects.items.Apple;
import com.github.hackertechmaster.hackertechrpg.objects.items.Knife;
import com.github.hackertechmaster.hackertechrpg.objects.items.OrangeJuice;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.ui.EntryUI;
import com.github.hackertechmaster.hackertechrpg.util.GameMap;
import com.github.hackertechmaster.hackertechrpg.registry.ItemRegistry;
import com.github.hackertechmaster.hackertechrpg.registry.PlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.util.Tick;

import java.util.Scanner;

public class Launcher {
    public static AbstractPlayerRegistry playerManager;
    public static ItemRegistry itemRegistry;
    public static GameMap gameMap;
    public static Tick tick;
    public static Scanner scanner;

    static {
        playerManager = new PlayerRegistry();
        itemRegistry = new ItemRegistry();
        gameMap = new GameMap(playerManager);
        tick = new Tick(playerManager);
        scanner = new Scanner(System.in);

        itemRegistry.register("Apple", Apple.class);
        itemRegistry.register("Knife", Knife.class);
        itemRegistry.register("OrangeJuice", OrangeJuice.class);
    }

    public static void main(String[] args) {
        //显示程序主入口菜单（注册/登录）
        EntryUI.INSTANCE.start();
        //登陆后新开一个线程，定期执行handleTickEvent
    }

    public static void quit() {
        //
    }
}
