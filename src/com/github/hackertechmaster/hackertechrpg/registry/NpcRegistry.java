package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.enums.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Npc;

import java.util.*;

public class NpcRegistry {
    private Map<Area, List<Npc>> npcMap = new HashMap<>();

    public void register(Npc npc) {
        Area area = npc.getArea();
        List<Npc> npcList;
        if(npcMap.containsKey(area)) {
            npcList = npcMap.get(area);
            npcList.add(npc);
        } else {
            npcList = new ArrayList<>();
            npcList.add(npc);
            npcMap.put(area, npcList);
        }
    }

    public List<Npc> getNpcsInArea(Area area) {
        return npcMap.getOrDefault(area, Collections.emptyList());
    }
}
