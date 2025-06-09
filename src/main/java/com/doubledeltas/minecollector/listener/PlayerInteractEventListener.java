package com.doubledeltas.minecollector.listener;

import com.doubledeltas.minecollector.GameDirector;
import com.doubledeltas.minecollector.MineCollector;
import com.doubledeltas.minecollector.item.itemCode.StaticItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInteractEventListener implements Listener {
    @EventHandler
    public void handleEvent(PlayerInteractEvent e) {
        ItemStack collectionBook = MineCollector.getInstance().getItemManager().getItem(StaticItem.COLLECTION_BOOK);
        Player player = e.getPlayer();
        PlayerInventory playerInventory = player.getInventory();

        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;

        if (!playerInventory.getItemInMainHand().equals(collectionBook)
                && !playerInventory.getItemInOffHand().equals(collectionBook))
            return;

        e.setCancelled(true);

        GameDirector.tryOpenHubGui(player);
    }
}
