package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
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

public class RessuciteCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
            if(hPlayer == null)
                return false;

            if(args.length != 2){
                p.sendMessage(ChatColor.RED + "Commande invalide.");
                return true;
            }

            if(hPlayer.getRole().isRole(Role.RIKA_FURUDE)){

                Player target = Bukkit.getPlayer(args[1]);
                if(target == null){
                    p.sendMessage(ChatColor.RED + "Player non trouvé");
                    return true;
                }
                HPlayer hPlayerTarget = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
                if(hPlayerTarget == null){
                    p.sendMessage(ChatColor.RED + "Player non trouvé");
                    return true;
                }

                if(((DeathTask) hPlayerTarget.getDeathTask()).isRunning()){

                    if(((RikaFurudeAction) hPlayer.getRole().getRoleAction()).getRessucite()){
                        p.sendMessage("Vous ne pouvez plus ressuciter");
                        return true;
                    }

                    ((RikaFurudeAction) hPlayer.getRole().getRoleAction()).resurrection(hPlayer, hPlayerTarget);
                    p.sendMessage("Vous venez de réssuciter " + hPlayerTarget.getName());
                    return true;

                }

            }

        }

        return false;
    }
}
