package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

public class ClansCmd extends CommandsExecutor {

    public ClansCmd() {
        super("[Clans]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {
        if (Clans.getClans(hPlayer) != null)
            return false;
        if (strings.length != 2)
            return false;
        Clans clans = Clans.getClans(strings[1]);
        if (clans == null)
            return false;

        if (!(clans.isClans(Clans.HINAMIZAWA) || clans.isClans(Clans.MERCENAIRE)) && hPlayer.getRole().isRole(Role.KYOSUKE_IRIE))
            return false;

        clans.addPlayer(hPlayer);
        return true;
    }
}
