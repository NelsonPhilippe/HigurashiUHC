package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozaki.removeHearth;

public class MionSonozaki extends Role implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME, MemberOfClub.getClans(), 1);
        this.setDisplayName("Shion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRole().equals(RoleList.MION_SONOZAKI.getRole())){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
        HPlayer playerAlive =  RoleList.SHION_SONOSAKI.getRole().getPlayer();


        if (deathPlayer.getRole().getName().equals(RoleList.MION_SONOZAKI.getRole().getName())) {

            if (playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {
                removeHearth(e, deathPlayer, playerAlive);
            }

        }
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer, HPlayer killed) {

    }
}
