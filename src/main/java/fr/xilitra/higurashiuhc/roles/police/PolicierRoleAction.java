package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class PolicierRoleAction implements RoleAction {

    @Override
    public String getDescription() {
        return "§6Vous êtes §ePolicier (non-genré) : \n" +
                "\n" +
                "§6Le §epolicier §6doit gagner avec §9Hinamizawa §6et fait partie du camp de la §epolice. \n" +
                "§6Le §epolicier §6a le pouvoir de mettre des contraventions avec la commande §5“/h pv <joueur>” §6(une fois dans la partie). \n" +
                "§6Le joueur ayant la contravention perd un slot de cœur pendant 10 minutes.";
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

}
