package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;

public class Okonogi extends Role {
    public Okonogi() {
        super("Okonogi", Gender.HOMME, Mercenaire.getClans());
    }

}
