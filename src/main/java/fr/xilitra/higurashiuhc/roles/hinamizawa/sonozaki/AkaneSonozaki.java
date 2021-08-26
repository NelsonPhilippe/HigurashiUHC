package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;

public class AkaneSonozaki extends Role {

    private int nextDaySwap;
    private int swapUsed;

    public AkaneSonozaki() {
        super("Akane Sonozaki", Gender.FEMME, Sonozaki.getClans());
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
}
