package com.github.hackertechmaster.hackertechrpg.ui;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.ICart;
import com.github.hackertechmaster.hackertechrpg.interfaces.IOrder;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;
import com.github.hackertechmaster.hackertechrpg.objects.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.hackertechmaster.hackertechrpg.Launcher.gameScanner;
import static com.github.hackertechmaster.hackertechrpg.interfaces.IOrder.BusinessType.BUYING;
import static com.github.hackertechmaster.hackertechrpg.interfaces.IOrder.BusinessType.SELLING;

public class CartUI extends BaseUI implements ICart {
    private static final char BACK = 'b'; //Back to MainUI
    private Map<Character, IOrder> charToOrder;
    private Map<String, String> nameToInfo;
    private final Npc npc;

    private CartUI(Npc npc) {
        this.npc = npc;
        refreshShopData();
    }

    public static CartUI of(Npc npc) {
        return new CartUI(npc);
    }

    private void refreshShopData() {
        List<IOrder> shopItems = npc.getOrders();
        charToOrder = IntStream.range(0, shopItems.size())
                .boxed()
                .collect(Collectors.toMap(i -> (char)(i+NUMBER_TO_CHAR_OFFSET), shopItems::get));
        nameToInfo = npc.getOrders()
                .stream()
                .collect(Collectors.toMap(IOrder::getItemName, order ->
                        String.format("%s%s, 单价%d， 剩余%d",
                                order.getBusinessType().getDescription(),
                                order.getItemName(),
                                order.getPrice(),
                                order.getAmount()))); //出售苹果，单价5, 剩余3
    }

    @Override
    public void showMenu() {
        System.out.println("=== 黑科技RPG V1.0 交易界面 ===");
        charToOrder.forEach((ch, order) -> System.out.println(String.format("[%c] %s", ch, nameToInfo.get(order.getItemName()))));
    }

    @Override
    public void handleBack() {
        MainUI.INSTANCE.start();
    }

    @Override
    public void handleInput(char input) {
        final boolean hasNpc = npc != null;
        if(hasNpc) {
            IOrder order = charToOrder.get(input);
            final boolean hasOrder = order != null;
            if(hasOrder) {
                IOrder.BusinessType businessType = order.getBusinessType();
                Player player = Launcher.playerRegistry.getCurrentPlayer();
                int amount = Integer.parseInt(gameScanner.readLine("请输入数量"));
                BusinessResult businessResult = BusinessResult.NOT_HANDLED;
                if(businessType == BUYING) {
                    businessResult = sellItem(player, order, amount);
                } else if(businessType == SELLING) {
                    businessResult = purchaseItem(player, order, amount);
                }
                System.out.println(businessResult.getDescription());
                start();
            } else {
                super.handleInput(input);
            }
        } else {
            super.handleInput(input);
        }
    }

    @Override
    public Map<String, String> getAllItemsInfo() {
        return nameToInfo;
    }

    @Override
    public BusinessResult purchaseItem(Player player, IOrder order, int amount) {
        String itemName = order.getItemName();
        if(!npc.getOrders().contains(order)) return BusinessResult.ITEM_NOT_FOUND;
        int sellingAmount = order.getAmount();
        if(order.getBusinessType() != SELLING) {
            //该类商品交易类型应为收购，所以无法玩家无法从此处购买物品
            return BusinessResult.BUSINESS_TYPE_NOT_MATCHED;
        } else if(sellingAmount < amount) {
            return BusinessResult.ITEM_NOT_ENOUGH;
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
                refreshShopData();
                return BusinessResult.OK;
            } else {
                return BusinessResult.INVENTORY_FULL;
            }
        } else {
            return BusinessResult.MONEY_NOT_ENOUGH;
        }
    }

    @Override
    public BusinessResult sellItem(Player player, IOrder order, int amount) {
        String itemName = order.getItemName();
        if(!npc.getOrders().contains(order)) return BusinessResult.ITEM_NOT_FOUND;
        int buyingAmount = order.getAmount();
        if(order.getBusinessType() != BUYING) {
            //该类商品交易类型应为出售，所以无法玩家无法从此处售出物品
            return BusinessResult.BUSINESS_TYPE_NOT_MATCHED;
        } else if(buyingAmount < amount) {
            return BusinessResult.ITEM_TOO_MANY;
        }

        int price = order.getPrice();
        int moneyRemain = player.getMoney();
        int moneyIncoming = price*amount;
        //检查背包中是否有足够数量的此类物品
        if(player.getInventory().getItemCount(itemName) >= amount) {
            player.getInventory().removeItem(itemName, amount); //更新玩家背包
            order.setAmount(buyingAmount - amount); //更新NPC收购信息
            player.setMoney(moneyRemain + moneyIncoming); //加钱
            refreshShopData();
            return BusinessResult.OK;
        } else {
            return BusinessResult.ITEM_NOT_ENOUGH;
        }
    }
}
