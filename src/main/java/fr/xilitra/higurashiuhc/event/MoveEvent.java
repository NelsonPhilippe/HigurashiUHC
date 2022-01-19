package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojoAction;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        //if(HigurashiUHC.getGameManager().getStates() != GameStates.GAME) return;

        if (hPlayer == null || hPlayer.getRole() == null) return;

        if (hPlayer.getDeathTask() instanceof DeathTask) {
            if (((DeathTask) hPlayer.getDeathTask()).isRunning()) {
                System.out.println("dont move!");
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }

        Location blockLoc = e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation();


        if (((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).blockTraps.size() > 0) {

            for (Location loc : ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).blockTraps) {

                if (loc.getBlock().getLocation().equals(blockLoc)) {
                    HPlayer satokoHojo = Role.SATOKO_HOJO.getHPlayer();

                    if (satokoHojo == null || satokoHojo.getPlayer() == null || satokoHojo.getUUID().equals(p.getUniqueId())) {
                        return;
                    }

                    satokoHojo.getPlayer().sendMessage(p.getName() + " est " + hPlayer.getRole().getName());
                    loc.getBlock().setType(Material.SOIL);
                    ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).blockTraps.remove(loc);
                    break;

                }
            }
        }


    }

}
