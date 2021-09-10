package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();

        DamageListener.playDeath(HigurashiUHC.getGameManager().removePlayer(p.getUniqueId()), DeathReason.DISCONNECTED);
        p.getInventory().clear();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        hPlayer.setPlayerState(PlayerState.DISCONNECTED);
        if(hPlayer.getRole() == null)

        if(HigurashiUHC.getGameManager().getStates() != GameStates.GAME)
            HigurashiUHC.getGameManager().removePlayer(p.getUniqueId());

    }

}
