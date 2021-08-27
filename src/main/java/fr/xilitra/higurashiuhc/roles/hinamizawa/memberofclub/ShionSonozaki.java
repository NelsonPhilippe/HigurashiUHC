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

public class ShionSonozaki extends Role implements Listener {
    public ShionSonozaki() {
        super("Shion Sonozaki", Gender.FEMME, MemberOfClub.getClans());
        this.setDisplayName("Mion Sonozaki");
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRoleList().getRole().equals(RoleList.SHION_SONOSAKI.getRole())){
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


        if(deathPlayer.getRoleList().getRole().equals(RoleList.SHION_SONOSAKI.getRole())){

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
    public void onKill(HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer) {

    }
}
