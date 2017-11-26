package com.github.hackertechmaster.hackertechrpg.implement;

import com.github.hackertechmaster.hackertechrpg.Launcher;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.hackertechmaster.hackertechrpg.util.Console.print;
import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class Inventory extends AbstractInventory {
    private static final int INVENTORY_NORMAL_SIZE = 5;

    private static final int NO_MERGE = 0;
    private static final int PARTIAL_MERGE = 1;
    private static final int FULL_MERGE = 2;

    private final int size;
    private Map<Integer, AbstractItem> slots;

    public Inventory() {
        this.size = INVENTORY_NORMAL_SIZE;
        this.slots = new HashMap<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Map<Integer, AbstractItem> getSlots() {
        return slots;
    }

    /**
     * @deprecated  Not for public use.
     *    This method is expected to be retained only as a package
     *    private method.  Replaced by
     *    {@link #putItem(AbstractItem)} , {@link #removeItem(String, int)} and {@link #clearItem(String)}
     * @param index 槽位
     * @param item 物品
     * @return index是否超出范围
     */
    @Override
    @Deprecated
    public boolean setSlot(int index, AbstractItem item) {
        if(index < size) {
            slots.put(index, item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasPlaceFor(String name, int amount) {
        //先尝试找到空槽
        for (int i = 0; i < size; i++) {
            if(!slots.containsKey(i)) {
                return true;
            }
        }
        AbstractItem itemFromName = Launcher.itemRegistry.getItemByName(name, amount);
        int stackCapacity = itemFromName.stackCapacity();
        //测试是否可以堆叠
        final int remainSpaceToStack = slots.values()
                .stream()
                .filter(item -> item.getName().equals(name))
                .mapToInt(item -> stackCapacity-item.stackAvailable())
                .sum();
        return remainSpaceToStack > amount;
    }

    @Override
    public boolean putItem(AbstractItem item) {
        //先尝试找到空槽
        for (int i = 0; i < size; i++) {
            if(!slots.containsKey(i)) {
                slots.put(i, item);
                return true;
            }
        }
        compact();
        int itemRemain = item.stackAvailable();
        //尝试找到空槽或堆叠到已有物品上
        for (int i = 0; i < size && itemRemain > 0; i++) {
            if (slots.containsKey(i)) {
                AbstractItem itemInSlot = slots.get(i);
                itemRemain = addToItem(itemInSlot, item, itemRemain);
            } else {
                slots.put(i, item);
                return true;
            }
        }
        return itemRemain == 0;
    }

    @Override
    public void clearItem(String name) {
        slots.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName().equals(name))
                .map(Map.Entry::getKey)
                .forEach(index -> slots.remove(index));
    }

    @Override
    public void removeItem(String name, int amount) {
        List<AbstractItem> items = slots.values()
                .stream()
                .filter(item -> item.getName().equals(name))
                .collect(Collectors.toList());
        for(AbstractItem item : items) {
            final int available = item.stackAvailable();
            if(available >= amount) {
                item.setStackAvailable(available-amount);
                break;
            } else {
                item.setStackAvailable(0);
                amount -= available;
            }
        }
        compact();
    }

    @Override
    public void compact() {
        List<AbstractItem> itemList = new ArrayList<>(slots.values());
        stackItem(itemList);

        int cursor = 0;
        Map<Integer, AbstractItem> map = new HashMap<>();
        for(AbstractItem item : itemList) {
            if(item.stackAvailable() == 0) continue;
            map.put(cursor, item);
            cursor++;
        }
        this.slots = map; //Update slots
    }

    /**
     * 堆叠所有物品
     * @param itemList 物品列表
     */
    private void stackItem(List<AbstractItem> itemList) {
        int size = itemList.size();
        for (int i = 0; i < size; i++) {
            AbstractItem masterItem = itemList.get(i);
            for (int j = i+1; j < size; j++) {
                AbstractItem slaveItem = itemList.get(j);
                mergeItems(masterItem, slaveItem);
                if(masterItem.stackAvailable() == masterItem.stackCapacity()) break; //Change masterItem cause it is full
            }
        }
    }

    /**
     * shorthand method
     * @param masterItem
     * @param slaveItem
     */
    private void mergeItems(AbstractItem masterItem, AbstractItem slaveItem) {
        addToItem(masterItem, slaveItem, slaveItem.stackAvailable());
    }

    /**
     * 尝试将指定数量的slaveItem堆叠至 masterItem，此方法可能会改变masterItem和slaveItem
     * @param masterItem
     * @param slaveItem
     * @param amount 指定堆叠移动数量最大值
     * @return 堆叠后slaveItem的剩余数量
     */
    private int addToItem(AbstractItem masterItem, AbstractItem slaveItem, int amount) {
        if(slaveItem.stackAvailable() == 0) return 0; //Fast return
        if(masterItem.stackAvailable() < masterItem.stackCapacity()) {
            final int stackAmountCanAdd = masterItem.stackCapacity() - masterItem.stackAvailable();
            final int updatedStackAmount = stackAmountCanAdd > amount ? amount : stackAmountCanAdd;
            final int stackOfMaster = masterItem.stackAvailable() + updatedStackAmount;
            final int stackOfSlave = slaveItem.stackAvailable() - updatedStackAmount;

            masterItem.setStackAvailable(stackOfMaster);
            slaveItem.setStackAvailable(stackOfSlave);
            return amount - updatedStackAmount;
        }
        return 0;
    }

    @Override
    public int getItemCount(String name) {
        return slots.values().stream()
                .filter(item -> name.equals(item.getName()))
                .mapToInt(AbstractItem::stackAvailable)
                .sum();
    }

    @Override
    public void show() {
        println("=== Inventory ===");
        slots.forEach((key, item) -> {
            final int index = key;
            print(String.format("[%d] ", index));
            item.show();
        });
    }
}
