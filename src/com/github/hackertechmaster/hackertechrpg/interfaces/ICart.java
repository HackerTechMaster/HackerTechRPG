package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface ICart {
    int OK = 0;
    int ITEM_NOT_ENOUGH = 1; //该物品剩余数量不足
    int ITEM_TOO_MANY = 2; //该物品数量过多
    int INVENTORY_FULL = 3; //背包已满
    int MONEY_NOT_ENOUGH = 4; //货币不足
    int ITEM_NOT_FOUND = 5; //该类物品不在交易列表中
    int BUSINESS_TYPE_NOT_MATCHED = 6; //不存在该类（出售或收购）交易

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
     * @param player 玩家
     * @param name 物品id
     * @param amount 购买数量
     * @return 购买结果，返回常量
     */
    int purchaseItem(AbstractPlayer player, String name, int amount);

    /**
     * 出售物品
     * @param player 玩家
     * @param name 物品id
     * @param amount 购买数量
     * @return 出售结果，返回常量
     */
    int sellItem(AbstractPlayer player, String name, int amount);
}
