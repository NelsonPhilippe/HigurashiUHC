package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class PolicierRoleAction implements RoleAction {

    @Override
    public Role getLinkedRole(){
        return Role.POLICIER;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §ePolicier (non-genré) : \n" +
                "\n" +
                "§6Le §epolicier §6doit gagner avec §9Hinamizawa §6et fait partie du camp de la §epolice. \n" +
                "§6Le §epolicier §6a le pouvoir de mettre des contraventions avec la commande §5'/h "+ Commands.PVCMD.getInitials() +" <joueur>' §6(une fois dans la partie). \n" +
                "§6Le joueur ayant la contravention perd un slot de cœur pendant 10 minutes.";
    }

}
