package com.github.hackertechmaster.hackertechrpg.registry;

import com.github.hackertechmaster.hackertechrpg.interfaces.IPrintable;
import com.github.hackertechmaster.hackertechrpg.objects.Area;
import com.github.hackertechmaster.hackertechrpg.objects.Inventory;
import com.github.hackertechmaster.hackertechrpg.objects.Player;

import java.util.HashMap;
import java.util.Map;

import static com.github.hackertechmaster.hackertechrpg.util.Console.println;

public class PlayerRegistry implements IPrintable {
    private Player currentPlayer;
    private final Map<String, Player> playerMap = new HashMap<>();

    public boolean register(String name) {
        if(playerMap.containsKey(name)) {
            return false;
        } else {
            playerMap.put(name, new Player(name, Area.DORMITORY, new Inventory()));
            return true;
        }
    }

    public boolean loginWithName(String name) {
        if(playerMap.containsKey(name)) {
            this.currentPlayer = playerMap.get(name);
            return true;
        } else {
            return false;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player findPlayerByName(String name) {
        return playerMap.get(name);
    }

    @Override
    public void show() {
        println("=== AllPlayer ==");
        playerMap.keySet().forEach(name -> println(String.format("[%s]", name)));
    }
}
