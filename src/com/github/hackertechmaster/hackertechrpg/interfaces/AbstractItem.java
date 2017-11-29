package com.github.hackertechmaster.hackertechrpg.interfaces;

import static com.github.hackertechmaster.hackertechrpg.util.Console.print;

public abstract class AbstractItem implements IPrintable {
    private int stackAvailable;
    private final ItemType type;
    private final String name;
    private final int stackCapacity;

    public AbstractItem(String name, ItemType type, int stackCapacity, int stackAvailable) {
        this.name = name;
        this.type = type;
        this.stackCapacity = stackCapacity;
        this.stackAvailable = stackAvailable > stackCapacity ? stackCapacity : stackAvailable;
    }

    public abstract AbstractItem copy(int stackAvailable);

    /**
     * 枚举类，物品类型
     */
    public enum ItemType {
        VEGETABLE("蔬菜"),
        FRUIT("水果"),
        TOOL("工具"),
        DRINK("饮料");

        private String description;

        ItemType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * @return 当前物品类型
     */
    public ItemType getType() {
        return type;
    }

    /**
     * @return 物品名称
     */
    public String getName() {
        return name;
    }

    /**
     * @return 该物品在一个格子中最大堆叠数量，该数量最少为1
     */
    public int stackCapacity() {
        return stackCapacity;
    }

    /**
     * @return 该物品当前数量,小于零且存在于NPC背包中则代表收购，其他情况不允许出现小于零的数
     */
    public int stackAvailable() {
        return stackAvailable;
    }

    /**
     * @param stackAvailable 新的物品当前数量
     */
    public void setStackAvailable(int stackAvailable) {
        this.stackAvailable = stackAvailable > this.stackCapacity ? this.stackCapacity : stackAvailable;
    }

    @Override
    public void show() {
        print(String.format("%s .. %d/%d", name, stackAvailable, stackCapacity));
    }
}
