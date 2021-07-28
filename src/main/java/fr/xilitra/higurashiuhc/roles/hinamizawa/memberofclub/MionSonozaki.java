package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozaki.removeHearth;

public class MionSonozaki extends RoleTemplate implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayerWithRole(Role.MION_SONOZAKI);
        HPlayer playerAlive = HigurashiUHC.getGameManager().getPlayerWithRole(Role.SHION_SONOSAKI);
        removeHearth(e, deathPlayer, playerAlive);
    }

}
