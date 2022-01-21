package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.ChatTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCmd extends CommandsExecutor {

    public ListCmd() {
        super("[List]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 2) {

            Player target = Bukkit.getPlayer(strings[1]);

            if (target == null) return false;

            HPlayer hPlayerTarget = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
            if (hPlayerTarget == null) {
                sendError(p, "Cible Introuvable");
                return false;
            }

            if (hPlayerTarget.isChatOkonogi()) {
                sendError(p, "Le joueur est déjà dans le chat");
                return false;
            }

            sendOkay(p,"Vous avez rajouté le joueur au tchat, en esperant qu'il ne soir pas trop toxique");
                    new ChatTask(hPlayerTarget).runTaskTimer(1, 1);

            return true;
        }else sendError(p, "Merci de faire /h "+ Commands.LIST.getInitials() +" <joueur>");
        return false;
    }
}
