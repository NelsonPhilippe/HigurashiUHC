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

public class ShionSonozakiAction extends RoleAction implements Listener {

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getPlayer() != null && player.getRole().isRole(Role.SHION_SONOSAKI)){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    public static void removeHearth(HPlayer deathPlayer, HPlayer playerAlive) {
        if(deathPlayer == null) return;

        if(playerAlive != null && playerAlive.getPlayer() != null && playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR){

            playerAlive.getPlayer().setMaxHealth(20);

        }

    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if(killed.getPlayer() == null)
            return;

        if(killed.hasMarriedReason(Reason.DOLL_TRAGEDY))
            killed.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,9999,1), true);

        if(!killer.hasMaledictionReason(Reason.DOLL_TRAGEDY))
            return;

        if(!killed.getRole().isRole(Role.ORYO_SONOZAKI, Role.KIICHIRO_KIMIYOSHI, Role.SATOKO_HOJO, Role.MION_SONOZAKI))
            return;

        if(killer.hasDeathLinkReason(Reason.DOLL_TRAGEDY)){
            List<HPlayer> ltd = killer.getDeathLinkPlayer(Reason.DOLL_TRAGEDY);
            ltd.forEach((lp) -> killer.getLinkData(lp).setDeathLinked(null, true));
        }

        List<Role> roleList = new ArrayList<Role>(){{
            add(Role.ORYO_SONOZAKI);
            add(Role.KIICHIRO_KIMIYOSHI);
            add(Role.SATOKO_HOJO);
            add(Role.MION_SONOZAKI);
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

        HPlayer playerAlive =  Role.MION_SONOZAKI.getHPlayer();
        if(playerAlive == null) return;

        if(killed.getRole().equals(Role.SHION_SONOSAKI)){

            if(playerAlive.getPlayer() != null && playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR)
                removeHearth(killed, playerAlive);

            HPlayer kasai =  Role.KASAI.getHPlayer();

            if(kasai != null && kasai.getPlayer() != null)
                if(killed.getKiller() != null)
                    kasai.getPlayer().sendMessage(killed.getName() + " à été tué par " + killed.getKiller().getName());
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