package com.doubledeltas.minecollector.listener;

import com.doubledeltas.minecollector.MineCollector;
import com.doubledeltas.minecollector.data.GameData;
import com.doubledeltas.minecollector.event.TotalScoreModifiedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Logger;

public class GameDataListener implements Listener {
    private final Logger logger = MineCollector.getInstance().getLogger();

    @EventHandler(priority = EventPriority.MONITOR)
    public void depositMileageWhenTotalScoreIncreased(TotalScoreModifiedEvent event) {
        BigInteger delta = event.getAfter().subtract(event.getBefore()).multiply(new BigDecimal(1000)).toBigIntegerExact();
        if (delta.signum() != 1) {
            // ignore if before >= after
            return;
        }

        GameData data = event.getGameData();
        BigInteger beforeMileage = data.getMileage();
        BigInteger afterMileage = data.depositMileage(delta);
        logger.info(String.format(
                "deposit to player %s mileage of %s (value updated %s -> %s)",
                event.getPlayer().getName(), delta, beforeMileage.toString(), afterMileage.toString()
        ));
    }
}
