package fr.xilitra.higurashiuhc.roles.hinamizawa;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class VillageoisAction implements RoleAction {

    @Override
    public Role getLinkedRole(){
        return Role.VILLAGEOIS;
    }

    @Override
    public String getDescription() {
        return "\n" +
                "§6Vous êtes §9Villageois (non-genré) : \n" +
                "\n" +
                "§6Vous devez gagner avec §9Hinamizawa. \n" +
                "§6vous ne possédez aucun pouvoir ni aucun effet particulier. ";
    }

}
