package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;

public class Nomura extends Role {
    public Nomura() {
        super("Nomura", Gender.FEMME, Neutre.getClans());
    }

}
