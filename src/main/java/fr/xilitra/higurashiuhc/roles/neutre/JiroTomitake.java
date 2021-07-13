package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class JiroTomitake extends RoleTemplate {
    public JiroTomitake() {
        super("Jiro Tomitake", Gender.HOMME);
    }

    @Override
    public void rollEffect(HPlayer player) {

    }
}
