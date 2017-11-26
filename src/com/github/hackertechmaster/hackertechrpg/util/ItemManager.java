package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {
    private Map<String, Class<? extends AbstractItem>> itemMap = new HashMap<>();

    public ItemManager() {}

    public void register(String name, Class<? extends AbstractItem> itemClass) {
        itemMap.put(name, itemClass);
    }

    public void unregister(String name) {
        itemMap.remove(name);
    }

    public AbstractItem getItemByName(String name, int stackAvailable) {
        Class<? extends AbstractItem> clazz = itemMap.get(name);
        if(clazz != null) {
            try {
                AbstractItem item = clazz.newInstance();
                item.setStackAvailable(stackAvailable);
                return item;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
