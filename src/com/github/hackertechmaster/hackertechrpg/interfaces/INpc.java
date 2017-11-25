package com.github.hackertechmaster.hackertechrpg.interfaces;

import java.util.Map;

public interface INpc extends IEntity {
    /**
     * @return 剩余货币
     */
    int getMoney();

    /**
     * @return 该NPC是否已经被激活
     */
    int isActive();

    /**
     * @return 商店信息（物品对应价格）
     */
    Map<AbstractItem, Integer> getShopMap();
}
