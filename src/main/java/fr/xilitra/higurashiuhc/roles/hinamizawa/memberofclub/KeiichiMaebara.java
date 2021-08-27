package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
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

public class KeiichiMaebara extends Role implements Listener {

    public KeiichiMaebara() {
        super("Keiichi Maebara", Gender.HOMME, MemberOfClub.getClans());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();

        HPlayer player = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(player.getRoleList().getRole().equals(RoleList.HANYU.getRole())){
            p.setGameMode(GameMode.SPECTATOR);
        }
    }


    @Override
    public void onKill(HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer) {

    }
}
