package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class NullAction implements RoleAction{
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Role getLinkedRole() {
        return Role.NULL;
    }
}
