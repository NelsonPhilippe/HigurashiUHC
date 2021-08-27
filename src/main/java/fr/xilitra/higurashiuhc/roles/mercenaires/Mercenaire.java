package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

import java.util.ArrayList;
import java.util.List;

public class Mercenaire extends Role {

    private HPlayer cible;
    private List<HPlayer> listMercenary = new ArrayList<>();

    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE, fr.xilitra.higurashiuhc.game.clans.Mercenaire.getClans());
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }

    public void addPlayerToMercenary(HPlayer hPlayer){
        this.listMercenary.add(hPlayer);
    }

    public void removePlayerToMercenary(HPlayer hPlayer){
        this.listMercenary.remove(hPlayer);
    }

    public List<HPlayer> getListMercenary() {
        return listMercenary;
    }
}
