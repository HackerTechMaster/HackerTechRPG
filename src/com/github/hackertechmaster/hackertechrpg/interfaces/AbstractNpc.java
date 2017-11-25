package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.util.Area;

import java.util.Map;

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
     * @param cnt
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
     * @return 商店信息（物品对应价格）
     */
    public abstract Map<AbstractOrderItem, Integer> getShopMap();

    /**
     * @param shopMap 要设置的商店信息
     */
    public abstract void setShopMap(Map<AbstractOrderItem, Integer> shopMap);
}
