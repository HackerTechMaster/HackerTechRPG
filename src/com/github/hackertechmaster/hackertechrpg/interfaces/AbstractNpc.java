package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.objects.Area;

import java.util.List;

public abstract class AbstractNpc extends AbstractEntity {
    public AbstractNpc(String name, Area area) {
        super(name, area);
    }

    /**
     * @return 剩余货币
     */
    public abstract int getMoney();

    /**
     * 设置剩余货币
     * @param cnt 新的货币数值
     */
    public abstract void setMoney(int cnt);

    /**
     * @return 该NPC是否已经被激活
     */
    public abstract boolean isActive();

    /**
     * @param active 更改状态
     */
    public abstract void setActive(boolean active);

    /**
     * @return 商店订单信息
     */
    public abstract List<IOrderEntry> getOrders();

    /**
     * @param orders 要设置的商店订单信息
     */
    public abstract void setOrders(List<IOrderEntry> orders);
}
