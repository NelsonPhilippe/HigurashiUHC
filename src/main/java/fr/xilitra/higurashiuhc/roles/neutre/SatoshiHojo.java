package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;

public class SatoshiHojo extends Role {
    public SatoshiHojo() {
        super("Satoshi Hojo", Gender.HOMME, Neutre.getClans());
    }


}
