package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class AkaneSonozaki extends Role {

    private int nextDaySwap;
    private int swapUsed;

    public AkaneSonozaki() {
        super("Akane Sonozaki", Gender.FEMME, ClansList.SONOZAKI, 1);
        swapUsed = 0;
        nextDaySwap = 0;
    }

    public int getNextDaySwap() {
        return nextDaySwap;
    }

    public void setNextDaySwap(int nextDaySwap) {
        this.nextDaySwap = nextDaySwap;
    }

    public int getSwapUsed() {
        return swapUsed;
    }

    public void setSwapUsed(int swapUsed) {
        this.swapUsed = swapUsed;
    }

    @Override
    public String getDecription() {
        return "null";
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
