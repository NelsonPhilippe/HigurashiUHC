package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Mercenaire extends Role {

    private HPlayer cible;

    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE, MercenaireClan.getClans());
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }
}
