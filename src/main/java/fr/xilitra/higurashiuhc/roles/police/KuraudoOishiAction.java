package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

    public boolean isCoupableDesigned() {
        return coupableIsDesigned;
    }

    public void setCoupableDesigned(boolean coupableIsDesigned) {
        this.coupableIsDesigned = coupableIsDesigned;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    @EventHandler
    public void wataChange(WatanagashiChangeEvent watanagashiChangeEvent){
        if(watanagashiChangeEvent.getWataEnum() != WataEnum.DURING)
            return;
        HPlayer player = Role.KURAUDO_OISHI.getHPlayer();

        if (player != null && player.getPlayer() != null && !isCoupableDesigned())
            player.getPlayer().setMaxHealth(2.5);
    }

}
