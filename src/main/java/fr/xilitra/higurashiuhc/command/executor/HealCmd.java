package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCmd extends CommandsExecutor {

    public HealCmd() {
        super("[Heal]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {
        if (strings.length == 2) {
            Player target = Bukkit.getPlayer(strings[1]);

            if (target == null) {
                sendMessage(p, ChatColor.RED, "Cible non trouvé");
                return false;
            }

            if (p.getHealth() <= 1) {
                sendMessage(p, ChatColor.RED, "Vous ne possédez pas assez de vie pour en donner.");
                return false;
            }

            p.setMaxHealth(p.getMaxHealth() - 1);
            target.setHealth(target.getHealth() + 2);
            sendMessage(p, ChatColor.GREEN, "Vie restaurée");
            return true;
        }
        return false;
    }
}
