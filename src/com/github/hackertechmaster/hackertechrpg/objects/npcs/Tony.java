package com.github.hackertechmaster.hackertechrpg.objects.npcs;

import com.github.hackertechmaster.hackertechrpg.interfaces.IOrder;
import com.github.hackertechmaster.hackertechrpg.objects.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;
import com.github.hackertechmaster.hackertechrpg.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class Tony extends Npc {
    private static final List<IOrder> orders;

    static {
        orders = new ArrayList<>();
        Order.Builder builder = new Order.Builder();
        orders.add(builder.item("Apple").price(2).amount(10).sell());
        orders.add(builder.item("Knife").price(5).amount(3).buy());
    }

    public Tony() {
        super("Tony", Area.CYBERCAFE, true, orders);
    }
}
