package com.doubledeltas.minecollector.listener;

import com.doubledeltas.minecollector.MineCollector;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

public class EventManager {
    private static List<Listener> listeners = Arrays.asList(
            new PlayerInteractEventListener(),
            new PlayerLoginEventListener(),
            new PlayerAdvancementDontEventListener(),
            new EntityDeathEventListener(),
            new GameDataListener()
    );

    public static void loadEventHandlers() {
        for (Listener listener: listeners) {
            MineCollector.getInstance().getServer().getPluginManager().registerEvents(listener, MineCollector.getInstance());
        }
    }

}
