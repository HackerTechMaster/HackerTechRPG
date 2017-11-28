package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.ICart;
import com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry.BusinessType.BUYING;
import static com.github.hackertechmaster.hackertechrpg.interfaces.IOrderEntry.BusinessType.SELLING;
import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class CartUI extends BaseUI implements ICart {
    private static final char BACK = 'b'; //Back to MainUI

    private static final int NUMBER_TO_CHAR_OFFSET = '0';
    private Map<Character, IOrderEntry> charToItem;
    private Map<String, String> nameToInfo;
    private final AbstractNpc npc;

    private CartUI(AbstractNpc npc) {
        this.npc = npc;
        refreshShopData();
    }

    public static ICart of(AbstractNpc npc) {
        return new CartUI(npc);
    }

    private void refreshShopData() {
        List<IOrderEntry> shopItems = npc.getOrders();
        charToItem = IntStream.range(0, shopItems.size())
                .boxed()
                .collect(Collectors.toMap(i -> (char)(i+NUMBER_TO_CHAR_OFFSET), shopItems::get));
        nameToInfo = npc.getOrders()
                .stream()
                .collect(Collectors.toMap(IOrderEntry::getItemName, order ->
                        String.format("%s%s, 单价%d， 剩余%d",
                                order.getBusinessType().getDescription(),
                                order.getItemName(),
                                order.getPrice(),
                                order.getAmount()))); //出售苹果，单价5, 剩余3
    }

    @Override
    public void showMenu() {
        //List<String> itemsInfo = getAllItemsInfo();
        println("=== AllItems ===");
        println("输入");
    }

    @Override
    public void handleInput(char input) {
        final boolean hasNpc = npc != null;
        if(hasNpc) {
            //Try purchase item
            //TODO stay at this cart until player quit this menu
        }
        if(input == BACK) {
            MainUI.INSTANCE.start();
        }
    }

    @Override
    public Map<String, String> getAllItemsInfo() {
        return nameToInfo;
    }

    @Override
    public int purchaseItem(AbstractPlayer player, IOrderEntry order, int amount) {
        String itemName = order.getItemName();
        if(!npc.getOrders().contains(order)) return ITEM_NOT_FOUND;
        int sellingAmount = order.getAmount();
        if(order.getBusinessType() == SELLING) {
            //该类商品交易类型应为收购，所以无法玩家无法从此处购买物品
            return BUSINESS_TYPE_NOT_MATCHED;
        } else if(sellingAmount < amount) {
            return ITEM_NOT_ENOUGH;
        }

        int price = order.getPrice();
        int moneyRequired = price*amount;
        int moneyRemain = player.getMoney();
        //看你这小子钱够不够
        if(moneyRemain >= moneyRequired) {
            //检查背包是否还有空间
            if(player.getInventory().hasPlaceFor(itemName, amount)) {
                player.setMoney(moneyRemain - moneyRequired); //扣钱
                order.setAmount(sellingAmount - amount);
                player.getInventory().putItem(Launcher.itemRegistry.getItem(itemName, amount)); //更新玩家背包
                return OK;
            } else {
                return INVENTORY_FULL;
            }
        } else {
            return MONEY_NOT_ENOUGH;
        }
    }

    @Override
    public int sellItem(AbstractPlayer player, IOrderEntry order, int amount) {
        String itemName = order.getItemName();
        if(!npc.getOrders().contains(order)) return ITEM_NOT_FOUND;
        int buyingAmount = order.getAmount();
        if(order.getBusinessType() == BUYING) {
            //该类商品交易类型应为出售，所以无法玩家无法从此处售出物品
            return BUSINESS_TYPE_NOT_MATCHED;
        } else if(buyingAmount < amount) {
            return ITEM_TOO_MANY;
        }

        int price = order.getPrice();
        int moneyRemain = player.getMoney();
        int moneyIncoming = price*amount;
        //检查背包中是否有足够数量的此类物品
        if(player.getInventory().getItemCount(itemName) >= amount) {
            player.getInventory().removeItem(itemName, amount); //更新玩家背包
            order.setAmount(buyingAmount - amount); //更新NPC收购信息
            player.setMoney(moneyRemain + moneyIncoming); //加钱
            return OK;
        } else {
            return ITEM_NOT_ENOUGH;
        }
    }
}
