package com.doubledeltas.minecollector.listener;

import com.doubledeltas.minecollector.MineCollector;
import com.doubledeltas.minecollector.event.TotalScoreModifiedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class GameDataListener implements Listener {
    private final Logger logger = MineCollector.getInstance().getLogger();

    @EventHandler(priority = EventPriority.MONITOR)
    public void depositMileageWhenTotalScoreIncreased(TotalScoreModifiedEvent event) {
        BigDecimal delta = event.getAfter().subtract(event.getBefore());
        if (delta.signum() != 1) {
            // ignore if before >= after
            return;
        }

        logger.info(String.format("deposit to player %s mileage of %s", event.getPlayer().getName(), delta.toPlainString()));
        event.getGameData().depositMileage(delta);
    }
}
