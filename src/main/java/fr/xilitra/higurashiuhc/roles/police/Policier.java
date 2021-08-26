package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;

public class Policier extends Role {

    private boolean pvIsUsed;

    public Policier() {
        super("Policier", Gender.NON_GENRE, Police.getClans());
        pvIsUsed = false;
    }

    public boolean isPvIsUsed() {
        return pvIsUsed;
    }

    public void setPvIsUsed(boolean pvIsUsed) {
        this.pvIsUsed = pvIsUsed;
    }
}
