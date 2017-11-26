package com.github.hackertechmaster.hackertechrpg.util;

import com.github.hackertechmaster.hackertechrpg.implement.Inventory;
import com.github.hackertechmaster.hackertechrpg.implement.Player;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayer;
import com.github.hackertechmaster.hackertechrpg.interfaces.AbstractPlayerManager;

import java.util.HashMap;
import java.util.Map;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class PlayerManager extends AbstractPlayerManager {
    private AbstractPlayer currentPlayer;
    private Map<String, AbstractPlayer> playerMap = new HashMap<>();

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
