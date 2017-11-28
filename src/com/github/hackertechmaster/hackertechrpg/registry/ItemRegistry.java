package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractItem;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private Map<String, AbstractItem> itemMap = new HashMap<>();

    public ItemRegistry() {}

    public void register(AbstractItem item) {
        itemMap.put(item.getName(), item);
    }

    public void unregister(String name) {
        itemMap.remove(name);
    }

    public AbstractItem getItem(String name) {
        return itemMap.get(name);
    }

    public AbstractItem getItem(String name, int stackAvailable) {
        AbstractItem item = itemMap.get(name);
        return item==null ? null : item.copy(stackAvailable);
    }
}
