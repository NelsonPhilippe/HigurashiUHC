package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;

public class TeppeiHojo extends Role {
    public TeppeiHojo() {
        super("Teppei Hojo", Gender.HOMME, Neutre.getClans());
    }


}
