package com.doubledeltas.minecollector;

import com.doubledeltas.minecollector.command.CommandRoot;
import com.doubledeltas.minecollector.config.ConfigManager;
import com.doubledeltas.minecollector.config.InvalidConfigException;
import com.doubledeltas.minecollector.config.McolConfig;
import com.doubledeltas.minecollector.data.DataAutoSaver;
import com.doubledeltas.minecollector.data.DataManager;
import com.doubledeltas.minecollector.event.EventManager;
import com.doubledeltas.minecollector.item.ItemManager;
import com.doubledeltas.minecollector.item.manager.InlineItemManager;
import com.doubledeltas.minecollector.util.MessageUtil;
import live.luya.minecollector.MineCollectorSyncServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class MineCollector extends JavaPlugin {
    private final ItemManager itemManager = new InlineItemManager();
    private McolConfig config;

    public static MineCollector getInstance() {
        return MineCollector.getPlugin(MineCollector.class);
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    @Override
    public void onEnable() {
        DataManager.loadData();
        EventManager.loadEventHandlers();
        CommandRoot.loadCommands();
        try {
            this.config = ConfigManager.load();
        } catch (InvalidConfigException e) {
            e.printStackTrace();
        }
        new MineCollectorSyncServer().start();
        DataAutoSaver.start();
        MessageUtil.log(Level.INFO, "마인콜렉터 플러그인이 켜졌습니다!");
    }

    @Override
    public void onDisable() {
        DataManager.saveAll();
        MessageUtil.log(Level.INFO, "마인콜렉터 플러그인이 꺼졌습니다.");
    }

    public McolConfig getMcolConfig() {
        return this.config;
    }

    public void reloadMcolConfig() throws InvalidConfigException {
        this.config = ConfigManager.load();
        DataAutoSaver.restart();
    }
}
