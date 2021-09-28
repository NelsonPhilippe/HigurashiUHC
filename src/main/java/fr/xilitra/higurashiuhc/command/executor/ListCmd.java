package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.ChatTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {

        Player p = hPlayer.getPlayer();
        if(p == null)
            return false;

        if (strings.length == 2){

            Player target = Bukkit.getPlayer(strings[1]);

            if(target == null) return true;

            HPlayer hPlayerTarget = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
            if(hPlayerTarget == null){
                p.sendMessage("Cible Introuvable");
                return true;
            }

            if(hPlayerTarget.isChatOkonogi()) {
                p.sendMessage("Le joueur est déjà dans le chat");
                return true;
            }

            new ChatTask(hPlayerTarget).runTask(1000,1000);

            return true;
        }
        return false;
    }
}
