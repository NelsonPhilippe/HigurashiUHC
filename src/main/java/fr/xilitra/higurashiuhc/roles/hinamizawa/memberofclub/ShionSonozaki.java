package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.Reason;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ShionSonozaki extends Role implements Listener {
    public ShionSonozaki() {
        super("Shion Sonozaki", Gender.FEMME, MemberOfClub.getClans(), 1);
        this.setDisplayName("Mion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRole().equals(RoleList.SHION_SONOSAKI.getRole())){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Player killer = e.getEntity().getKiller();
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
        HPlayer playerAlive =  RoleList.MION_SONOZAKI.getRole().getPlayer();


        if(deathPlayer.getRole().equals(RoleList.SHION_SONOSAKI.getRole())){

            if(playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR){
                removeHearth(e, deathPlayer, playerAlive);
            }

            HPlayer kasai =  RoleList.KASAI.getRole().getPlayer();

            if(kasai != null){
                kasai.getPlayer().sendMessage(p.getName() + " à été tué par " + killer.getName());
            }
        }
    }

    public static void removeHearth(PlayerDeathEvent e, HPlayer deathPlayer, HPlayer playerAlive) {
        Player p = e.getEntity();

        if(deathPlayer != null){

            if(p != deathPlayer.getPlayer()) return;

            if(playerAlive != null && playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR){

                playerAlive.getPlayer().setMaxHealth(20);

            }

        }
    }

    @Override
    public void onKill(EntityDamageEvent de, HPlayer killer, HPlayer killed) {

        if(killed.hasMariedReason(Reason.DOLL_TRAGEDY))
            killed.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,9999,1), true);

        if(!killer.hasMaledictionReason(Reason.DOLL_TRAGEDY))
            return;

        if(!killed.getRole().isRole(RoleList.ORYO_SONOZAKI.getRole(), RoleList.KIICHIRO_KIMIYOSHI.getRole(), RoleList.SATOKO_HOJO.getRole(), RoleList.MION_SONOZAKI.getRole()))
            return;

        if(killer.hasDeathLinkReason(Reason.DOLL_TRAGEDY)){
            HPlayer ltd = killer.getDeathLinkPlayer(Reason.DOLL_TRAGEDY);
            if(ltd != null) {
                killer.removeDeathLink(ltd);
                ltd.removeDeathLink(killer);
            }
        }

        List<Role> roleList = new ArrayList<Role>(){{
            add(RoleList.ORYO_SONOZAKI.getRole());
            add(RoleList.KIICHIRO_KIMIYOSHI.getRole());
            add(RoleList.SATOKO_HOJO.getRole());
            add(RoleList.MION_SONOZAKI.getRole());
        }};

        killer.getPlayer().setMaxHealth(killed.getPlayer().getMaxHealth()+1);

        for(Role role : roleList){
            Role killerRole = role.getPlayer().getKillerRole();
            if(killerRole == null)
                return;
            if(killerRole != this)
                return;
        }

        killer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1), true);

    }

    @Override
    public void onDeath(EntityDamageEvent de, HPlayer killed) {

    }
}
