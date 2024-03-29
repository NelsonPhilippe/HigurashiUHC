package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurudeAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RessuciteCmd extends CommandsExecutor {

    public RessuciteCmd() {
        super("[Ressucite]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] args) {

        if (args.length != 2) {
            sendError(p, ChatColor.RED + "Commande invalide, merci de faire /h "+ Commands.RESSUCITE.getInitials() +" (Joueur)");
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sendError(p, ChatColor.RED + "Player non trouvé");
            return false;
        }

        HPlayer hPlayerTarget = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
        if (hPlayerTarget == null) {
            sendError(p, ChatColor.RED + "Player non trouvé");
            return false;
        }

        if (((DeathTaskExecutor) hPlayerTarget.getDeathTask()).isRunning()) {

            ((RikaFurudeAction) hPlayer.getRole().getRoleAction()).resurrection(hPlayer, hPlayerTarget);
            sendOkay(p, "Vous venez de ressusciter " + hPlayerTarget.getName());
            return true;

        } else
            sendError(p, "Impossible de le ressusciter");

        return false;
    }
}
