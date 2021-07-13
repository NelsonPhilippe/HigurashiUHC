package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Hanyu extends RoleTemplate {
    public Hanyu() {
        super("Hanyu", Gender.FEMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
