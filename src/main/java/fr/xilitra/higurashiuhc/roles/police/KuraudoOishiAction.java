package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class KuraudoOishiAction extends RoleAction implements Listener {


    private final List<HPlayer> suspect = new ArrayList<>();
    private int countSuspect = 0;
    private boolean coupableIsDesigned = false;


    public int getCountSuspect() {
        return countSuspect;
    }

    public void setCountSuspect(int countSuspect) {
        this.countSuspect = countSuspect;
    }

    public List<HPlayer> getSuspect() {
        return suspect;
    }

    public void addSuspect(HPlayer hPlayer) {
        this.suspect.add(hPlayer);
    }

    public boolean isCoupableIsDesigned() {
        return coupableIsDesigned;
    }

    public void setCoupableIsDesigned(boolean coupableIsDesigned) {
        this.coupableIsDesigned = coupableIsDesigned;
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

}
