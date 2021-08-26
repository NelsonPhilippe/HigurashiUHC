package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class KuraudoOishi extends Role implements Listener {


    private List<HPlayer> suspect = new ArrayList<>();
    private int countSuspect;
    private boolean coupableIsDesigned;


    public KuraudoOishi() {
        super("Kuraudo Oishi", Gender.HOMME, Police.getClans());
        countSuspect = 0;
        coupableIsDesigned = false;
    }


    public int getCountSuspect() {
        return countSuspect;
    }

    public void setCountSuspect(int countSuspect) {
        this.countSuspect = countSuspect;
    }

    public List<HPlayer> getSuspect() {
        return suspect;
    }

    public void addSuspect(HPlayer hPlayer){
        this.suspect.add(hPlayer);
    }

    public boolean isCoupableIsDesigned() {
        return coupableIsDesigned;
    }

    public void setCoupableIsDesigned(boolean coupableIsDesigned) {
        this.coupableIsDesigned = coupableIsDesigned;
    }

    public enum infoList {

        KILL("Nombre de kill"),
        DIAMOND("Nombre de diamand"),
        EFFECT("Effet sur le joueur"),
        COMMANDS("Commande accessible par le joueur"),
        APPLE("Nombre de pomme dorée"),
        ROLE("Lettre du role"),
        SPECIAL_ITEM("Item special possédé par le joueur"),
        SEXE("Sexe du joueur");

        String type;

        infoList(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
