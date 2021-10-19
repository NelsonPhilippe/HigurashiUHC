package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
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
    public void onPlayerLeave(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null) return;

        if(HigurashiUHC.getGameManager().getStates() == GameStates.GAME && hPlayer.getPlayerState() == PlayerState.INGAME)
            DamageListener.playDeath(HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId()), DeathReason.DISCONNECTED);

        p.getInventory().clear();

        hPlayer.setPlayer(null);

        Clans clans = Clans.getClans(hPlayer);
        if (clans != null)
            clans.removePlayer(hPlayer);

        hPlayer.setPlayerState(PlayerState.DISCONNECTED);
        if (hPlayer.getRole() == null)
            if (HigurashiUHC.getGameManager().getStates() != GameStates.GAME)
                HigurashiUHC.getGameManager().removePlayer(p.getUniqueId());

    }

}
