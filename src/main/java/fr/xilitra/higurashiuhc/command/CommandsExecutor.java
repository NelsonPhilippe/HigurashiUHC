package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.player.HPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public abstract class CommandsExecutor {

    final String prefix;

    public CommandsExecutor(String prefix) {
        this.prefix = prefix + " ";
    }

    public abstract boolean onCommand(HPlayer hPlayer, Player player, String[] strings);

    public void sendError(HPlayer hPlayer, String message) {
        sendError(hPlayer.getPlayer(), message);
    }

    public void sendError(Player player, String message) {
        if (player != null)
            player.sendMessage(prefix + ChatColor.RED + message);
    }

    public void sendOkay(HPlayer hPlayer, String message) {
        sendOkay(hPlayer.getPlayer(), message);
    }

    public void sendOkay(Player player, String message) {
        if (player != null)
            player.sendMessage(prefix + ChatColor.GREEN + message);
    }

    public void sendMessage(HPlayer hPlayer, ChatColor chatColor, String message) {
        sendMessage(hPlayer.getPlayer(), chatColor, message);
    }

    public void sendMessage(Player player, ChatColor chatColor, String message) {
        if (player != null)
            player.sendMessage(prefix + chatColor + message);
    }

}
