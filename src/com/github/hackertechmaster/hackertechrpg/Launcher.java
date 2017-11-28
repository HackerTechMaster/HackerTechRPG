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

public class Launcher {
    public static AbstractPlayerRegistry playerRegistry;
    public static ItemRegistry itemRegistry;
    public static NpcRegistry npcRegistry;
    public static GameMap gameMap;
    public static Tick tick;
    public static Scanner scanner;

    static {
        playerRegistry = new PlayerRegistry();
        itemRegistry = new ItemRegistry();
        npcRegistry = new NpcRegistry();
        gameMap = new GameMap(playerRegistry);
        tick = new Tick(playerRegistry);
        scanner = new Scanner(System.in);

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
        //登陆后新开一个线程，定期执行handleTickEvent
    }

    public static void quit() {
        //TODO 先清理再关闭
        System.exit(0);
    }
}
