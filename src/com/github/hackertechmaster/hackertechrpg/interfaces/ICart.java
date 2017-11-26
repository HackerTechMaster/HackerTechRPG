package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface ICart {
    int OK = 0;
    int ITEM_NOT_ENOUGH = 1; //该物品剩余数量不足
    int INVENTORY_FULL = 2; //背包已满
    int MONEY_NOT_ENOUGH = 3; //货币不足
    int BUSINESS_TYPE_NOT_MATCHED = 4; //不存在该类（出售或收购）交易
    int ITEM_NOT_FOUND = 5; //该类物品不在交易列表中

    /**
     * 传入商品信息，包装成商城界面，实现控制台操作
     * @param npc npc实例
     * @return 包装后的商城界面
     */
    ICart of(AbstractNpc npc);

    /**
     * 显示所有商品信息
     */
    void showAllItemsInfo();

    /**
     * 购买物品
     * @param name 物品id
     * @return 购买结果，返回常量 {@value #OK}, {@value #INVENTORY_FULL}, {@value #MONEY_NOT_ENOUGH}， {@value #BUSINESS_TYPE_NOT_MATCHED}, {@value #ITEM_NOT_FOUND}
     */
    int purchaseItem(AbstractPlayer player, String name);

    /**
     * 出售物品
     * @param name 物品id
     * @return 出售结果，返回常量 {@value OK}, {@value ITEM_NOT_ENOUGH}, {@value #BUSINESS_TYPE_NOT_MATCHED}, {@value #ITEM_NOT_FOUND}
     */
    int sellItem(AbstractPlayer player, String name);
}
