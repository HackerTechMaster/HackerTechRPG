package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.objects.Player;

import java.util.Map;

public interface ICart {
    enum BusinessResult {
        OK("成功"),
        ITEM_NOT_ENOUGH("该物品剩余数量不足"),
        ITEM_TOO_MANY("交易数量过多"),
        INVENTORY_FULL("背包已满"),
        MONEY_NOT_ENOUGH("节操不足"),
        ITEM_NOT_FOUND("该物品不在交易列表中"),
        BUSINESS_TYPE_NOT_MATCHED("错误的交易类型"),
        NOT_HANDLED("出现问题");

        private String description;

        BusinessResult(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 返回所有商品信息
     */
    Map<String, String> getAllItemsInfo();

    /**
     * 购买物品
     * @param player 玩家
     * @param item 物品
     * @param amount 购买数量
     * @return 购买结果，返回常量
     */
    BusinessResult purchaseItem(Player player, IOrder item, int amount);

    /**
     * 出售物品
     * @param player 玩家
     * @param item 物品
     * @param amount 购买数量
     * @return 出售结果，返回常量
     */
    BusinessResult sellItem(Player player, IOrder item, int amount);
}
