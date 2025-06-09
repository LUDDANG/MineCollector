package com.doubledeltas.minecollector.event;

import com.doubledeltas.minecollector.data.GameData;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
public class TotalScoreModifiedEvent extends PlayerEvent {
    private final GameData gameData;
    private final BigDecimal before;
    private final BigDecimal after;

    public TotalScoreModifiedEvent(@NotNull Player who, GameData gameData, BigDecimal before, BigDecimal after) {
        super(who);
        this.gameData = gameData;
        this.before = before;
        this.after = after;
    }

    /* ------------------------------------------------------------ */
    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
