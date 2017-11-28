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
        orders.add(new OrderEntry.Builder().item("Apple").price(2).amount(10).sell());
        orders.add(new OrderEntry.Builder().item("Knife").price(5).amount(3).buy());
    }

    public Tony() {
        super("Tony", Area.CYBERCAFE, 50, true, orders);
    }
}
