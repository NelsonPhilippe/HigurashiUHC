package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsConfig implements CommandExecutor {

    public CommandsConfig() {
        HigurashiUHC.getInstance().getCommand("config").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))
            return false;

        Player player = ((Player) commandSender).getPlayer();
        if (player == null || !player.isOp())
            return false;

        if (HigurashiUHC.getGameManager().getStates() != GameStates.CONFIG) {
            player.sendMessage("La game à démarré");
            return true;
        }

        return true;
    }
}
