package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class TeppeiHojoAction implements RoleAction {

    @Override
    public Role getLinkedRole(){
        return Role.TEPPEI_HOJO;
    }

    @Override
    public String getDescription() {
        return null;
    }

}
