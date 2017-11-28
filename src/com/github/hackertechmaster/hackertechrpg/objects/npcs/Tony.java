package com.github.hackertechmaster.hackertechrpg.objects.npcs;

import com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry;
import com.github.hackertechmaster.hackertechrpg.objects.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;
import com.github.hackertechmaster.hackertechrpg.objects.OrderEntry;

import java.util.ArrayList;
import java.util.List;

public class Tony extends Npc {
    private static final List<IOrderEntry> orders;

    static {
        orders = new ArrayList<>();
        //售出10苹果 价格 2
        orders.add(OrderEntry.sell("Apple", 2, 10));
        //收购3小刀 价格 5
        orders.add(OrderEntry.buy("Knife",5, 3));
    }

    public Tony() {
        super("Tony", Area.CYBERCAFE, 50, true, orders);
    }
}
