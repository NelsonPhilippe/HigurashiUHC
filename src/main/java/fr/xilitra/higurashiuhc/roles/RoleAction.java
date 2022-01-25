package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public interface RoleAction {

    default void playerLeave(Player p){

    }

    default boolean acceptReconnect(Player p){
        return false;
    }

    default void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason){

    }

    default boolean onDeath(HPlayer killed, DeathReason deathReason){
        return true;
    }

    default void onLeaveRole(HPlayer hPlayer){

    }

    default void onJoinRole(HPlayer hPlayer){

    }

    default void onGameStart(){

    }

    default void onGameStop(){

    }

    String getDescription();

    Role getLinkedRole();
    default void onMaledictionReceived(HPlayer hPlayer, Reason reason){

    }

}
