package com.github.hackertechmaster.hackertechrpg.implement;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractInventory;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

import java.util.*;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class Inventory extends AbstractInventory {
    private static int INVENTORY_NORMAL_SIZE = 5;

    private int size;
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

    @Override
    public void compact() {
        List<AbstractItem> itemList = new ArrayList<>(slots.values());
        stackItem(itemList);

        int cursor = 0;
        Map<Integer, AbstractItem> map = new HashMap<>();
        for(AbstractItem item : itemList) {
            map.put(cursor, item);
            cursor++;
        }
    }

    /**
     * 堆叠所有物品
     * @param itemList 物品列表
     */
    private void stackItem(List<AbstractItem> itemList) {
        int size = itemList.size();
        int cursor = 0;

        while(cursor+1<size) {
            final boolean mergeSuccess = mergeItems(itemList.get(cursor), itemList.get(cursor+1));
            if(mergeSuccess) {
                itemList.remove(cursor+1);
            } else {
                cursor++;
            }
        }
    }

    /**
     * 合并slaveItem到masterItem
     * @param masterItem
     * @param slaveItem
     * @return 成功则返回true，失败则返回false
     */
    private boolean mergeItems(AbstractItem masterItem, AbstractItem slaveItem) {
        if(masterItem.stackAvailable() < masterItem.stackCapacity()) {
            final int stackSum = masterItem.stackAvailable() + slaveItem.stackAvailable();
            if(stackSum <= masterItem.stackCapacity()) {
                masterItem.setStackAvailable(stackSum);
                slaveItem.setStackAvailable(0);
                return true;
            }
        }
        return false;
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
        slots.entrySet().forEach(entry -> {
            final int index = entry.getKey();
            final AbstractItem item = entry.getValue();
            println(String.format("[%d] %s .. %d/%d",
                    entry.getKey(), item.getName(), item.stackAvailable(), item.stackCapacity() ));
        });
    }
}
