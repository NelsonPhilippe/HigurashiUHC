package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozaki.removeHearth;

public class MionSonozaki extends Role implements Listener {
    public MionSonozaki() {
        super("Mion Sonozaki", Gender.FEMME, MemberOfClub.getClans(), 1);
        this.setDisplayName("Shion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getPlayer() != null && player.getRole().isRole(RoleList.MION_SONOZAKI.getRole())){
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if(!killer.hasMaledictionReason(Reason.DOLL_TRAGEDY))
            return;

        if(!killed.getRole().isRole(RoleList.RENA_RYUGU.getRole(), RoleList.SHION_SONOSAKI.getRole(), RoleList.SATOKO_HOJO.getRole(), RoleList.KEIICHI_MAEBARA.getRole()))
            return;

        if(killer.hasDeathLinkReason(Reason.DOLL_TRAGEDY)){
            List<HPlayer> ltd = killer.getDeathLinkPlayer(Reason.DOLL_TRAGEDY);
            ltd.forEach((pl) -> killer.getLinkData(pl).setDeathLinked(null, false));
        }

        List<Role> roleList = new ArrayList<Role>(){{
            add(RoleList.RENA_RYUGU.getRole());
            add(RoleList.SHION_SONOSAKI.getRole());
            add(RoleList.SATOKO_HOJO.getRole());
            add(RoleList.KEIICHI_MAEBARA.getRole());
        }};

        if(killer.getPlayer() != null)
            killer.getPlayer().setMaxHealth(killer.getPlayer().getMaxHealth()+1);

        for(Role role : roleList){
            if(role.getHPlayer() == null)
                return;
            Role killerRole = role.getHPlayer().getKillerRole();
            if(killerRole == null)
                return;
            if(killerRole != this)
                return;
        }

        killer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1), true);

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        HPlayer playerAlive =  RoleList.SHION_SONOSAKI.getRole().getHPlayer();


        if (playerAlive != null && playerAlive.getPlayer() != null && killed.getRole().isRole(RoleList.MION_SONOZAKI.getRole())) {

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
