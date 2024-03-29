package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PenseCmd extends CommandsExecutor {

    public PenseCmd() {
        super("[Pense]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        HPlayer targetPlayer = HigurashiUHC.getGameManager().getHPlayer(strings[1]);

        if (targetPlayer == null) {
            sendError(p, "Le joueur n'est pas connecté.");
            return false;
        }

        ((RenaRyuguAction) hPlayer.getRole().getRoleAction()).setHPlayerPense(targetPlayer);
        sendOkay(p,"Vous lisez dans les pensées de " + strings[1]);

        return true;

    }
}
