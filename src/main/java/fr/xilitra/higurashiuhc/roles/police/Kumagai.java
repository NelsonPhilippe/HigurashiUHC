package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Kumagai extends RoleTemplate {
    public Kumagai() {
        super("Kumagai", Gender.HOMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
