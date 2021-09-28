package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class NullRoleAction extends RoleAction{

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason deathReason) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
