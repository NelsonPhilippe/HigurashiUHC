package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.VoteTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VoteCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        if(args.length == 1){
            if(VoteTask.isRunning){
                String target = args[0];

                HPlayer player = HigurashiUHC.getGameManager().getHPlayer(UUID.fromString(target));

                if(player == null)
                    return false;

                OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) player.getRole().getRoleAction();

                oryoSonozakiAction.addVote(player);

                new VoteTask().runTask(1000,1000);

                return true;
            }
        }

        return false;
    }
}
