package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.item.config.MapItemConfig;
import fr.xilitra.higurashiuhc.item.config.StartGameItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setMaxHealth(20);
        p.setHealth(20);

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null) {
            hPlayer = new HPlayer(p.getName(), p);
            HigurashiUHC.getGameManager().addHPlayer(hPlayer);
        }

        e.setJoinMessage("Bienvenue " + e.getPlayer().getName() + "!");

        if (HigurashiUHC.getGameManager().getStates() == GameStates.CONFIG) {

            if (p.hasPermission("huhc.hote.config")) {

                p.getInventory().setItem(8, StartGameItem.startGameItem.getItemStack());
                p.getInventory().setItem(0, MapItemConfig.mapItemConfig.getItemStack());

            }


        } else if (HigurashiUHC.getGameManager().getStates() != GameStates.START) {

            if (HigurashiUHC.getGameManager().getStates() == GameStates.GAME && hPlayer.getRole().getRoleAction().acceptReconnect(p)) {
                p.setGameMode(GameMode.SURVIVAL);
                return;
            }

            p.setGameMode(GameMode.SPECTATOR);
            hPlayer.setPlayerState(PlayerState.SPECTATE);

        }


    }

}
