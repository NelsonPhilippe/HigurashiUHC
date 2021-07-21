package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.DeathTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayers().get(p.getUniqueId());

        if(hPlayer.getDeathTask() instanceof DeathTask){
           if(((DeathTask) hPlayer.getDeathTask()).isStarted()){
               e.setCancelled(true);
           }else {
               e.setCancelled(false);
           }
        }
    }

}
