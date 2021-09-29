package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsListener  implements CommandExecutor {

    public CommandsListener(){
        HigurashiUHC.getInstance().getCommand("h").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return false;

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(((Player) commandSender).getUniqueId());
        if(hPlayer == null)
            return false;
        Player player = Bukkit.getPlayer(hPlayer.getUuid());
        if(player == null)
            return false;

        if(strings.length < 1){
            player.sendMessage("Merci de faire /h (commande)");
            return true;
        }

        Commands commands = Commands.getCommands(strings[0]);
        if(commands == null){
            player.sendMessage("Commande non reconnue");
            return true;
        }

        if(!hPlayer.getRole().hasCommandAccess(commands)){
            player.sendMessage("Vous ne pouvez pas / plus utiliser cette commande");
            return true;
        }

        if(commands.getCommandExecutor().onCommand(hPlayer, strings))
            hPlayer.getRole().useCommand(commands);

        return true;
    }
}
