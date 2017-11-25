package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface IEntity {
    /**
     * @return 实体名称
     */
    String getName();

    /**
     * @return 返回该实体的背包
     */
    AbstractInventory getInventory();
}
