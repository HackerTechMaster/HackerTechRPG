package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractNpc;
import com.github.hackertechmaster.hackertechrpg.objects.Area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpcRegistry {
    private Map<Area, List<AbstractNpc>> npcMap = new HashMap<>();
}
