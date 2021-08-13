package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Policier extends RoleTemplate {

    private boolean pvIsUsed;

    public Policier() {
        super("Policier", Gender.NON_GENRE);
        pvIsUsed = false;
    }

    public boolean isPvIsUsed() {
        return pvIsUsed;
    }

    public void setPvIsUsed(boolean pvIsUsed) {
        this.pvIsUsed = pvIsUsed;
    }
}
