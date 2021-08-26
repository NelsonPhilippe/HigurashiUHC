package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class Mercenaire extends RoleTemplate {

    private HPlayer cible;

    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE);
        this.clan = HigurashiUHC.getGameManager().getMercenaire();
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }
}
