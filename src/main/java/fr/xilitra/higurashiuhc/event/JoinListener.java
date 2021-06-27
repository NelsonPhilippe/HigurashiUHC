package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
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

        if(HigurashiUHC.getGameManager().getStates() == GameStates.CONFIG){

            if(p.hasPermission("huhc.hote.config")){
                scoreboard.setLines(
                        "",
                        "Configurer la partie",
                        "",
                        "ip: 127.0.0.1"
                );
            }

            scoreboard.setLines(
                    "",
                    "L'hôte configure la partie",
                    "",
                    "ip: 127.0.0.1"
            );
        }

        HigurashiUHC.addScoreboard(p.getUniqueId(), scoreboard);

        e.setJoinMessage("Bienvenue " + e.getPlayer().getName() + "!");


    }

}
