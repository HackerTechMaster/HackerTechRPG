package com.github.hackertechmaster.hackertechrpg.objects.npcs;

import com.github.hackertechmaster.hackertechrpg.interfaces.IOrder;
import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;
import com.github.hackertechmaster.hackertechrpg.objects.Order;

import java.util.ArrayList;
import java.util.List;

public class Liaolun extends Npc {
    private static final List<IOrder> orders;

    static {
        orders = new ArrayList<>();
        Order.Builder builder = new Order.Builder();
        orders.add(builder.item("Apple").price(4).amount(99).buy());
        orders.add(builder.item("Knife").price(10).amount(99).sell());
    }

    public Liaolun() {
        super("Liaolun", Area.CYBERCAFE, true, orders);
    }
}
