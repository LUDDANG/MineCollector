package com.doubledeltas.minecollector.util;

import com.doubledeltas.minecollector.data.DataManager;
import com.doubledeltas.minecollector.data.GameData;
import com.doubledeltas.minecollector.data.GameStatistics;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class GameDataUtil {
    public static BigDecimal getPlayerTotalScore(Player player) {
        return getPlayerTotalScore(DataManager.getData(player));
    }

    public static BigDecimal getPlayerTotalScore(GameData data) {
        GameStatistics stats = new GameStatistics(data);
        return stats.getTotalScore();
    }
}
