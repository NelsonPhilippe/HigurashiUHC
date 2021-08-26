package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozaki.removeHearth;

public class MionSonozaki extends RoleTemplate implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME);
        this.setDisplayName("Shion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRole().getName().equalsIgnoreCase("Mion Sonozaki")){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
        HPlayer playerAlive = HigurashiUHC.getGameManager().getPlayerWithRole(Role.SHION_SONOSAKI);


        if (deathPlayer.getRole().getClass().getName().equals(Role.MION_SONOZAKI.getRole().getName())) {

            if (playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {
                removeHearth(e, deathPlayer, playerAlive);
            }

        }
    }

}
