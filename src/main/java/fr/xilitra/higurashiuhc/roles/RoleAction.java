package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public interface RoleAction {

    void playerLeave(Player p);

    boolean acceptReconnect(Player p);

    void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason);

    void onDeath(HPlayer killed, DeathReason deathReason);

    void onLeaveRole(HPlayer hPlayer);

    void onJoinRole(HPlayer hPlayer);

    void onGameStart();

    void onGameStop();

    String getDescription();

    default void onMaledictionReceived(HPlayer hPlayer, Reason reason){

    }

}
