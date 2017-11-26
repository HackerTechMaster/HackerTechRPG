package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.objects.Area;

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
     * @return 商店信息（物品对应数量）,数量为正则是售出，数量为负则是收购
     */
    public abstract Map<AbstractOrderItem, Integer> getShopMap();

    /**
     * @param shopMap 要设置的商店信息
     */
    public abstract void setShopMap(Map<AbstractOrderItem, Integer> shopMap);
}
