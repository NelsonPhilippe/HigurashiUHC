package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Policier extends RoleTemplate {
    public Policier() {
        super("Policier", Gender.NON_GENRE);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
