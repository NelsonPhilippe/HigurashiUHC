package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurude;
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

            if(hPlayer.getRole().getName().equals(RoleList.RIKA_FURUDE.getRole().getName())){

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

                    if(((RikaFurude) hPlayer.getRole()).getRessucite()){
                        p.sendMessage("Vous ne pouvez plus ressuciter");
                        return true;
                    }

                    ((RikaFurude) hPlayer.getRole()).resurrection(hPlayer, hPlayerTarget);
                    p.sendMessage("Vous venez de réssuciter " + hPlayerTarget.getName());
                    return true;

                }

            }

        }

        return false;
    }
}
