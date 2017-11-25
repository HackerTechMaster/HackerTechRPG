package com.github.hackertechmaster.hackertechrpg.interfaces;

import java.util.Map;

public interface ICart {
    int OK = 0;
    int ITEM_NOT_ENOUGH = 1; //该物品剩余数量不足
    int INVENTORY_FULL = 2; //背包已满
    int MONEY_NOT_ENOUGH = 3; //货币不足
    int EXCEPTION = 4; //其他异常情况

    /**
     * 传入商品信息，包装成商城界面，实现控制台操作
     * @param shopMap
     * @return 包装后的商城界面
     */
    ICart of(Map<AbstractItem, Integer> shopMap);

    /**
     * 显示所有商品信息
     */
    void showAllItemsInfo();

    /**
     * @return 玩家是否处于当前商城
     */
    boolean isActive();

    /**
     * 玩家打开该商城界面
     */
    void active();

    /**
     * 玩家离开该商城界面
     */
    void inactive();

    /**
     * 购买物品
     * @param index 物品id
     * @return 购买结果，返回常量 {@value #OK}, {@value #INVENTORY_FULL}, {@value #MONEY_NOT_ENOUGH}, {@value #EXCEPTION}
     */
    boolean purchaseItem(int index);

    /**
     * 出售物品
     * @param index 物品id
     * @return 出售结果，返回常量 {@value OK}, {@value ITEM_NOT_ENOUGH}, {@value #EXCEPTION}
     */
    boolean sellItem(int index);
}
