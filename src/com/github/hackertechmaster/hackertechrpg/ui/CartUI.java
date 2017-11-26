package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractOrderItem;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.ICart;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class CartUI implements GameUserInterface, ICart {
    private final AbstractNpc npc;

    private CartUI(AbstractNpc npc) {
        this.npc = npc;
    }

    @Override
    public ICart of(AbstractNpc npc) {
        return new CartUI(npc);
    }

    @Override
    public void showMenu() {
        //
    }

    @Override
    public void handleInput(int input) {
        //
    }

    @Override
    public void showAllItemsInfo() {
        println("=== AllItems ===");
        npc.getShopMap().forEach((key, value) -> {
            String itemName = key.getName();
            int requireAmount = value;
            String type;
            int amount;
            if(requireAmount > 0) {
                type = "出售";
                amount = requireAmount;
            } else if(requireAmount < 0) {
                type = "收购";
                amount = - requireAmount;
            } else {
                type = "待激活";
                amount = 0;
            }
            println(String.format("[%s] .. %s%d", itemName, type, amount));
        });
    }

    @Override
    public int purchaseItem(AbstractPlayer player, String name, int amount) {
        return npc.getShopMap().entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findAny()
                .map(entry -> {
                    AbstractOrderItem item = entry.getKey();
                    int sellingAmount = entry.getValue();
                    int price = item.getPrice();
                    int moneyRemain = player.getMoney();
                    if(sellingAmount < 0) {
                        //该类商品交易类型应为收购，所以无法玩家无法从此处购买物品
                        return BUSINESS_TYPE_NOT_MATCHED;
                    } else if(sellingAmount < amount) {
                        return ITEM_NOT_ENOUGH;
                    }
                    if(moneyRemain >= price) {
                        //检查背包是否还有空间
                        if(player.getInventory().hasPlaceFor(name, amount)) {
                            player.setMoney(moneyRemain - price*amount); //扣钱
                            entry.setValue(sellingAmount - amount); //更新NPC出售信息
                            player.getInventory().putItem(Launcher.itemRegistry.getItemByName(name, amount)); //更新玩家背包
                            return OK;
                        } else {
                            return INVENTORY_FULL;
                        }
                    } else {
                        return MONEY_NOT_ENOUGH;
                    }
                }).orElse(ITEM_NOT_FOUND);
    }

    @Override
    public int sellItem(AbstractPlayer player, String name, int amount) {
        return npc.getShopMap().entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findAny()
                .map(entry -> {
                    AbstractOrderItem item = entry.getKey();
                    int buyingAmount = - entry.getValue(); //取反
                    int price = item.getPrice();
                    int moneyRemain = player.getMoney();
                    if(buyingAmount < 0) {
                        //该类商品交易类型应为出售，所以无法玩家无法从此处售出物品
                        return BUSINESS_TYPE_NOT_MATCHED;
                    } else if(buyingAmount < amount) {
                        return ITEM_TOO_MANY;
                    }
                    //检查背包中是否有足够数量的此类物品
                    if(player.getInventory().getItemCount(name) >= amount) {
                        player.getInventory().removeItem(name, amount); //更新玩家背包
                        entry.setValue(-(buyingAmount - amount)); //更新NPC收购信息（取反）
                        player.setMoney(moneyRemain + price*amount); //加钱
                        return OK;
                    } else {
                        return ITEM_NOT_ENOUGH;
                    }
                }).orElse(ITEM_NOT_FOUND);
    }
}
