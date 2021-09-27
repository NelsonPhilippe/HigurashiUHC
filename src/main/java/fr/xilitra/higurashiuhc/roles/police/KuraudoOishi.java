package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class KuraudoOishi extends Role implements Listener {


    private List<HPlayer> suspect = new ArrayList<>();
    private int countSuspect;
    private boolean coupableIsDesigned;


    public KuraudoOishi() {
        super("Kuraudo Oishi", Gender.HOMME, Clans.POLICE, 1);
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

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    public enum InfoList {

        KILL("Nombre de kill"),
        DIAMOND("Nombre de diamand"),
        EFFECT("Effet sur le joueur"),
        COMMANDS("Commande accessible par le joueur"),
        APPLE("Nombre de pomme dorée"),
        ROLE("Lettre du role"),
        SPECIAL_ITEM("Item special possédé par le joueur"),
        SEXE("Sexe du joueur");

        String type;

        InfoList(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }
}
