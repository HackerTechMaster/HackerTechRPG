package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.objects.Area;

import java.util.*;

public class NpcRegistry {
    private Map<Area, List<AbstractNpc>> npcMap = new HashMap<>();

    public void register(AbstractNpc npc) {
        Area area = npc.getArea();
        List<AbstractNpc> npcList;
        if(npcMap.containsKey(area)) {
            npcList = npcMap.get(area);
            npcList.add(npc);
        } else {
            npcList = new ArrayList<>();
            npcList.add(npc);
            npcMap.put(area, npcList);
        }
    }

    public List<AbstractNpc> getNpcsInArea(Area area) {
        return npcMap.getOrDefault(area, Collections.emptyList());
    }
}
