package com.github.hackertechmaster.hackertechrpg.interfaces;

public abstract class AbstractItem implements IPrintable {
    /**
     * 枚举类，物品类型
     */
    public enum ItemType {
        VEGETABLE,
        FRUIT,
        TOOL,
        DRINK
    }

    /**
     * @return 当前物品类型
     */
    public abstract ItemType getType();

    /**
     * @return 物品名称
     */
    public abstract String getName();

    /**
     * @return 该物品在一个格子中最大堆叠数量，该数量最少为1
     */
    public abstract int stackCapacity();

    /**
     * @return 该物品当前数量,小于零且存在于NPC背包中则代表收购，其他情况不允许出现小于零的数
     */
    public abstract int stackAvailable();

    /**
     * 设置该物品当前数量
     * @param stackAvailable
     */
    public abstract void setStackAvailable(int stackAvailable);
}
