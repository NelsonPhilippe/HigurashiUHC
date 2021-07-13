package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Okonogi extends RoleTemplate {
    public Okonogi() {
        super("Okonogi", Gender.HOMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
