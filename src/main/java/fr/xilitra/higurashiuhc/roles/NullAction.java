package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class NullAction implements RoleAction{
    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason deathReason) {

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
    public String getDescription() {
        return null;
    }

    @Override
    public Role getLinkedRole() {
        return Role.NULL;
    }
}