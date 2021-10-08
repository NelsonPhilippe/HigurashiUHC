package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public abstract class RoleAction {

    Role role;

    public Role getLinkedRole() {
        return role;
    }

    protected void setLinkedRole(Role role) {
        this.role = role;
    }

    public abstract void playerLeave(Player p);

    public abstract boolean acceptReconnect(Player p);

    public abstract void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason);

    public abstract void onDeath(HPlayer killed, DeathReason deathReason);

    public abstract void onLeaveRole(HPlayer hPlayer);

    public abstract void onJoinRole(HPlayer hPlayer);

    public abstract void onGameStart();

    public abstract void onGameStop();

}
