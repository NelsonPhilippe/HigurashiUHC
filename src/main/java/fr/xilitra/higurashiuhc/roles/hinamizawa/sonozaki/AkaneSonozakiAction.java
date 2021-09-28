package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class AkaneSonozakiAction extends RoleAction {

    private int nextDaySwap;

    public AkaneSonozakiAction() {
        nextDaySwap = 0;
    }

    public int getNextDaySwap() {
        return nextDaySwap;
    }

    public void setNextDaySwap(int nextDaySwap) {
        this.nextDaySwap = nextDaySwap;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
