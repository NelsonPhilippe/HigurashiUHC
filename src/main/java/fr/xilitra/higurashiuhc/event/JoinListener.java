package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Scoreboard scoreboard = new Scoreboard(ChatColor.BLUE + "HigurashiUHC", p);

        HigurashiUHC.addScoreboard(p.getUniqueId(), scoreboard);

        e.setJoinMessage("Bienvenue " + e.getPlayer().getName() + "!");
    }

}
