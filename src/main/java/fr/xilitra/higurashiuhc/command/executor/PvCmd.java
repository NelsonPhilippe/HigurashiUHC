package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.PolicierTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PvCmd extends CommandsExecutor {

    public PvCmd() {
        super("[PV]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 2) {
            Player target = Bukkit.getPlayer(strings[1]);

            if (target == null) {
                p.sendMessage("Le joueur n'existe pas");
                return false;
            }

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
            if (targetHPlayer == null) {
                p.sendMessage("Cible Introuvable");
                return false;
            }

            target.setMaxHealth(target.getMaxHealth() - 2);
            new PolicierTask(hPlayer).runTaskTimer(1, 1);
            p.sendMessage("Vous venez de mettre un pv à " + target.getName());

            return true;

        }
        return false;
    }
}
