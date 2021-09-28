package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurudeAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RessuciteCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] args) {

        Player p = hPlayer.getPlayer();

        if(p == null)
            return false;

        if(args.length != 2){
            p.sendMessage(ChatColor.RED + "Commande invalide, merci de faire /h r (Joueur)");
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if(target == null){
            p.sendMessage(ChatColor.RED + "Player non trouvé");
            return false;
        }

        HPlayer hPlayerTarget = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
        if(hPlayerTarget == null){
            p.sendMessage(ChatColor.RED + "Player non trouvé");
            return false;
        }

        if(((DeathTask) hPlayerTarget.getDeathTask()).isRunning()){

            ((RikaFurudeAction) hPlayer.getRole().getRoleAction()).resurrection(hPlayer, hPlayerTarget);
            p.sendMessage("Vous venez de réssuciter " + hPlayerTarget.getName());
            return true;

        }else
            p.sendMessage("Impossible de le réssuciter");

        return false;
    }
}
