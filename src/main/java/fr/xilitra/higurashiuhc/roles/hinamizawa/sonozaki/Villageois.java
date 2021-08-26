package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;

public class Villageois extends Role {
    public Villageois() {
        super("Villageois", Gender.NON_GENRE, Sonozaki.getClans());
    }


}
