package com.github.hackertechmaster.hackertechrpg.implement;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractOrderItem;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.ICart;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class Cart implements ICart {
    private final AbstractNpc npc;

    private Cart(AbstractNpc npc) {
        this.npc = npc;
    }

    @Override
    public ICart of(AbstractNpc npc) {
        return new Cart(npc);
    }

    @Override
    public void showAllItemsInfo() {
        println("=== AllItems ===");
        npc.getShopMap().forEach((key, value) -> {
            String itemName = key.getName();
            int requireAmount = value;
            println(String.format("[%s] .. %d", itemName, requireAmount));
        });
    }

    //FIXME 暂时只支持整个同类的物品打包买卖

    @Override
    public int purchaseItem(AbstractPlayer player, String name) {
        return npc.getShopMap().entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findAny()
                .map(entry -> {
                    AbstractOrderItem item = entry.getKey();
                    int requiredAmount = entry.getValue();
                    int price = item.getPrice();
                    int moneyRemain = player.getMoney();
                    if(requiredAmount < 0) {
                        //该类商品交易类型应为收购，所以无法玩家无法从此处购买物品
                        return BUSINESS_TYPE_NOT_MATCHED;
                    }
                    if(moneyRemain >= price) {
                        //检查背包是否还有空间
                        if(player.getInventory().hasPlaceFor(item)) {
                            player.setMoney(moneyRemain - price);
                            player.getInventory().putItem(item);
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
    public int sellItem(AbstractPlayer player, String name) {
        return npc.getShopMap().entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findAny()
                .map(entry -> {
                    AbstractOrderItem item = entry.getKey();
                    int requiredAmount = entry.getValue();
                    int price = item.getPrice();
                    int moneyRemain = player.getMoney();
                    if(requiredAmount > 0) {
                        //该类商品交易类型应为出售，所以无法玩家无法从此处售出物品
                        return BUSINESS_TYPE_NOT_MATCHED;
                    }
                    //检查背包中是否有足够数量的此类物品
                    if(player.getInventory().getItemCount(name) >= requiredAmount) {
                        player.getInventory().removeItem(name, item.stackAvailable());
                        player.setMoney(moneyRemain + price*item.stackAvailable());
                        return OK;
                    } else {
                        return ITEM_NOT_ENOUGH;
                    }
                }).orElse(ITEM_NOT_FOUND);
    }
}
