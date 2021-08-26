package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.item.config.MapItemConfig;
import fr.xilitra.higurashiuhc.item.config.StartGameItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
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
        p.setMaxHealth(20);
        p.setHealth(20);
        HPlayer hPlayer = new HPlayer(p.getName(), p.getUniqueId(), p);
        HigurashiUHC.getGameManager().addPlayer(hPlayer);
        Scoreboard scoreboard = new Scoreboard(ChatColor.DARK_PURPLE + "HigurashiUHC", p);

        if(p.isOp()){
            p.getInventory().setItem(8, StartGameItem.startGameItem.getItemStack());
            p.getInventory().setItem(0, MapItemConfig.mapItemConfig.getItemStack());
        }

        if(HigurashiUHC.getGameManager().getStates() == GameStates.CONFIG){

            if(p.hasPermission("huhc.hote.config")){
                scoreboard.setLines(
                        "",
                        ChatColor.GRAY + "Nombre de role : " + ChatColor.GOLD + RoleList.values().length,
                        "",
                        ChatColor.RED + "Titre de game",
                        "",
                        ChatColor.GRAY + "Configurer la partie...",
                        "",
                        ChatColor.DARK_PURPLE + "Okami Servers"
                );

                HigurashiUHC.addScoreboard(p.getUniqueId(), scoreboard);

                return;
            }

            scoreboard.setLines(
                    "",
                    ChatColor.GRAY + "Nombre de role : " + ChatColor.GOLD + RoleList.values().length,
                    "",
                    ChatColor.RED + "Titre de game",
                    "",
                    ChatColor.GRAY + "Configuration de la partie",
                    "",
                    ChatColor.DARK_PURPLE + "Okami Servers"
            );

            HigurashiUHC.addScoreboard(p.getUniqueId(), scoreboard);
        }

        HigurashiUHC.addScoreboard(p.getUniqueId(), scoreboard);

        e.setJoinMessage("Bienvenue " + e.getPlayer().getName() + "!");


    }

}
