package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class KasaiAction extends RoleAction {

    private boolean isGiveForce;

    public KasaiAction() {
        isGiveForce = false;
    }

    public boolean isGiveForce() {
        return isGiveForce;
    }

    public void setGiveForce(boolean giveForce) {
        isGiveForce = giveForce;
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
        HPlayer kasai = Role.KASAI.getHPlayer();
        HPlayer shion = Role.SHION_SONOSAKI.getHPlayer();

        if (kasai != null && kasai.getPlayer() != null && shion != null)
            kasai.getPlayer().sendMessage("Shion est joué par: " + shion.getName());
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
}
