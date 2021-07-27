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

public class MionSonozaki extends RoleTemplate implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayers().get(p.getUniqueId());

        if(hPlayer.getRole().getClass().equals(Role.MION_SONOZAKI.getRole())){



        }
    }

}
