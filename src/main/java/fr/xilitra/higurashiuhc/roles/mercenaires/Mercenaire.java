package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Mercenaire extends RoleTemplate {
    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
