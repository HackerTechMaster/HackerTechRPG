package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerRegistry;
import com.github.hackertechmaster.hackertechrpg.objects.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Inventory;
import com.github.hackertechmaster.hackertechrpg.objects.Player;

import java.util.HashMap;
import java.util.Map;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class PlayerRegistry extends AbstractPlayerRegistry {
    private AbstractPlayer currentPlayer;
    private final Map<String, AbstractPlayer> playerMap = new HashMap<>();

    @Override
    public boolean register(String name) {
        if(playerMap.containsKey(name)) {
            return false;
        } else {
            playerMap.put(name, new Player(name, Area.DORMITORY, new Inventory()));
            return true;
        }
    }

    @Override
    public boolean loginWithName(String name) {
        if(playerMap.containsKey(name)) {
            this.currentPlayer = playerMap.get(name);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public AbstractPlayer findPlayerByName(String name) {
        return playerMap.get(name);
    }

    @Override
    public void show() {
        println("=== AllPlayer ==");
        playerMap.keySet().forEach(name -> println(String.format("[%s]", name)));
    }
}
