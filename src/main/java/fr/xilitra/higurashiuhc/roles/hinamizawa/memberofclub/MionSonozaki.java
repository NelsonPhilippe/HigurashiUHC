package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozaki.removeHearth;

public class MionSonozaki extends Role implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME, MemberOfClub.getClans());
        this.setDisplayName("Shion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRoleList().getRole().equals(RoleList.MION_SONOZAKI.getRole())){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
        HPlayer playerAlive =  RoleList.SHION_SONOSAKI.getRole().getPlayer();


        if (deathPlayer.getRoleList().getRole().getName().equals(RoleList.MION_SONOZAKI.getRole().getName())) {

            if (playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {
                removeHearth(e, deathPlayer, playerAlive);
            }

        }
    }

}
