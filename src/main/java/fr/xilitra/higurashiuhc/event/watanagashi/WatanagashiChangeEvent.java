package fr.xilitra.higurashiuhc.event.watanagashi;

import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WatanagashiChangeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final WataEnum wataEnum;

    public WatanagashiChangeEvent(WataEnum wataEnum) {
        this.wataEnum = wataEnum;
    }

    public WataEnum getWataEnum() {
        return wataEnum;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }

}
