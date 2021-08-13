package fr.xilitra.higurashiuhc.event.higurashi;

import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RoleSelected extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private HPlayer player;

    public RoleSelected(HPlayer player){
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HPlayer getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
