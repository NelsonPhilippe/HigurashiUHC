package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.event.entity.EntityDamageEvent;

public class AkaneSonozaki extends Role {

    private int nextDaySwap;
    private int swapUsed;

    public AkaneSonozaki() {
        super("Akane Sonozaki", Gender.FEMME, Sonozaki.getClans(), 1);
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
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }
}
