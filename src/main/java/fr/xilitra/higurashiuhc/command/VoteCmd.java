package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.VoteTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VoteCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;


        Player p = (Player) sender;

        if(args.length == 1){
            if(VoteTask.isRunning){
                String target = args[0];

                HPlayer player = HigurashiUHC.getGameManager().getPlayer(UUID.fromString(target));

                OryoSonozaki oryoSonozaki = (OryoSonozaki) player.getRole();

                oryoSonozaki.addVote(player);

                Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new VoteTask(), 20, 20);

                return true;
            }
        }

        return false;
    }
}
