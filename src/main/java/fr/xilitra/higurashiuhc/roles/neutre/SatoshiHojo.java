package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class SatoshiHojo extends RoleTemplate {
    public SatoshiHojo() {
        super("Satoshi Hojo", Gender.HOMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
