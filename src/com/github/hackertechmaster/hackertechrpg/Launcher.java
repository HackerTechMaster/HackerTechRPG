package com.github.hackertechmaster.hackertechrpg;

import com.github.hackertechmaster.hackertechrpg.implement.items.Apple;
import com.github.hackertechmaster.hackertechrpg.implement.items.Knife;
import com.github.hackertechmaster.hackertechrpg.implement.items.OrangeJuice;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerManager;
import com.github.hackertechmaster.hackertechrpg.util.ItemManager;
import com.github.hackertechmaster.hackertechrpg.util.Map;
import com.github.hackertechmaster.hackertechrpg.util.PlayerManager;
import com.github.hackertechmaster.hackertechrpg.util.Tick;

public class Launcher {
    public static AbstractPlayerManager playerManager;
    public static ItemManager itemManager;
    public static Map map;
    public static Tick tick;

    static {
        playerManager = new PlayerManager();
        itemManager = new ItemManager();
        map = new Map(playerManager);
        tick = new Tick(playerManager);

        itemManager.register("Apple", Apple.class);
        itemManager.register("Knife", Knife.class);
        itemManager.register("OrangeJuice", OrangeJuice.class);
    }

    public static void main(String[] args) {
        //显示注册登录菜单
        //登陆后新开一个线程，定期执行handleTickEvent
        //显示操作菜单(地图-移动-查看，背包-查看，NPC-出售-购买-查看)
    }
}
