package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;

public class JiroTomitake extends Role {
    public JiroTomitake() {
        super("Jiro Tomitake", Gender.HOMME, Neutre.getClans());
    }

}
