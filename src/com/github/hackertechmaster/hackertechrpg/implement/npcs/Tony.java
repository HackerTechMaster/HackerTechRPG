package com.github.hackertechmaster.hackertechrpg.implement.npcs;

import com.github.hackertechmaster.hackertechrpg.implement.Npc;
import com.github.hackertechmaster.hackertechrpg.implement.OrderItem;
import com.github.hackertechmaster.hackertechrpg.implement.items.Apple;
import com.github.hackertechmaster.hackertechrpg.implement.items.Knife;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractOrderItem;
import com.github.hackertechmaster.hackertechrpg.util.Area;

import java.util.HashMap;
import java.util.Map;

public class Tony extends Npc {
    private static final Map<AbstractOrderItem, Integer> shopMap = new HashMap<>();

    static {
        shopMap.put(OrderItem.wrapItem(new Apple(), 2), 10); //售出10苹果
        shopMap.put(OrderItem.wrapItem(new Knife(), 5), 3); //收购3小刀
    }

    public Tony() {
        super("Tony", Area.CYBERCAFE, 50, true, shopMap);
    }
}
