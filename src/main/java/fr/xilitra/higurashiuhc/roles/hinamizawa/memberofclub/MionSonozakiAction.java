package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozakiAction.removeHearth;

public class MionSonozakiAction extends RoleAction implements Listener {

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getPlayer() != null && player.getRole().isRole(Role.MION_SONOZAKI)){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if(!killer.hasMaledictionReason(Reason.DOLL_TRAGEDY))
            return;

        if(!killed.getRole().isRole(Role.RENA_RYUGU, Role.SHION_SONOSAKI, Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA))
            return;

        if(killer.hasDeathLinkReason(Reason.DOLL_TRAGEDY)){
            List<HPlayer> ltd = killer.getDeathLinkPlayer(Reason.DOLL_TRAGEDY);
            ltd.forEach((pl) -> killer.getLinkData(pl).setDeathLinked(null, false));
        }

        List<Role> roleList = new ArrayList<Role>(){{
            add(Role.RENA_RYUGU);
            add(Role.SHION_SONOSAKI);
            add(Role.SATOKO_HOJO);
            add(Role.KEIICHI_MAEBARA);
        }};

        if(killer.getPlayer() != null)
            killer.getPlayer().setMaxHealth(killer.getPlayer().getMaxHealth()+1);

        for(Role role : roleList){
            if(role.getHPlayer() == null)
                return;
            Role killerRole = role.getHPlayer().getKillerRole();
            if(killerRole == null)
                return;
            if(killerRole != this.getLinkedRole())
                return;
        }

        killer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1), true);

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        HPlayer playerAlive =  Role.SHION_SONOSAKI.getHPlayer();

        if (playerAlive != null && playerAlive.getPlayer() != null && killed.getRole().isRole(Role.MION_SONOZAKI)) {

            if (playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {
                removeHearth(killed, playerAlive);
            }

        }

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
