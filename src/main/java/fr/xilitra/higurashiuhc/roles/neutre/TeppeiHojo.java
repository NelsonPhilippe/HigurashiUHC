package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class TeppeiHojo extends RoleTemplate {
    public TeppeiHojo() {
        super("Teppei Hojo", Gender.HOMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
