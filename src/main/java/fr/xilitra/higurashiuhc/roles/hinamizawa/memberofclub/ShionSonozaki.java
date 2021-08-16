package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ShionSonozaki extends RoleTemplate implements Listener {
    public ShionSonozaki() {
        super("Shion Sonozaki", Gender.FEMME);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        Player killer = e.getEntity().getKiller();
        HPlayer deathPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
        HPlayer playerAlive = HigurashiUHC.getGameManager().getPlayerWithRole(Role.MION_SONOZAKI);


        if(deathPlayer.getRole().getClass().getName().equals(Role.SHION_SONOSAKI.getRole().getName())){

            if(playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR){
                removeHearth(e, deathPlayer, playerAlive);
            }

            HPlayer kasai = HigurashiUHC.getGameManager().getPlayerWithRole(Role.KASAI);

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

}
