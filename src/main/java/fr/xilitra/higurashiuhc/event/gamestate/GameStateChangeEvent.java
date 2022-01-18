package fr.xilitra.higurashiuhc.event.gamestate;

import fr.xilitra.higurashiuhc.game.GameStates;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends Event {

    final GameStates newState;

    public GameStateChangeEvent(GameStates newState) {
        this.newState = newState;
    }

    public GameStates getNewGameState() {
        return newState;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public static HandlerList getHandlerList(){
        return null;
    }
}
