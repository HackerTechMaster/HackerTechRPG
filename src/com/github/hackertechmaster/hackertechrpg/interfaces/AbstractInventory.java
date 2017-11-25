package com.github.hackertechmaster.hackertechrpg.interfaces;

import java.util.Map;

public abstract class AbstractInventory implements IPrintable {
    /**
     * @return 该背包拥有的格子（储物空间大小）
     */
    public abstract int size();

    /**
     * @return 获取槽位信息，槽ID对应物品
     */
    public abstract Map<Integer, AbstractItem> getSlots();

    /**
     * @param index 槽位
     * @param item 物品
     * @return 是否放置成功
     */
    public abstract boolean setSlot(int index, AbstractItem item);

    /**
     * 将所有同类物品尽量堆叠在一起
     */
    public abstract void compact();

    /**
     * 获得指定物品在背包中的数量
     * @param name 物品名称
     * @return 数量
     */
    public abstract int getItemCount(String name);
}
