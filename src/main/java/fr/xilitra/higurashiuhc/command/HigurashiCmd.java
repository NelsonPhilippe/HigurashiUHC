package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HigurashiCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("pense")){

                HPlayer rena = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

                if(rena == null || rena.getPlayer().getUniqueId() != p.getUniqueId()) return true;

                if(((RenaRyugu) rena.getRole()).gethPlayerPense() == null) return true;

                for(HPlayer hPlayer : HigurashiUHC.getGameManager().getPlayers().values()){

                    if(hPlayer.getName().equalsIgnoreCase(args[1])){
                        ((RenaRyugu) rena.getRole()).setHPlayerPense(hPlayer);
                        break;
                    }

                }


            }
        }


        return false;
    }
}
