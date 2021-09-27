package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
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
    public void onMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        HPlayer rika =  RoleList.RIKA_FURUDE.getRole().getHPlayer();

        //if(HigurashiUHC.getGameManager().getStates() != GameStates.GAME) return;

        if(hPlayer == null || hPlayer.getRole() == null) return;

        if(rika != null && rika.getPlayer() != null)
        for(Role role : ClansList.MEMBER_OF_CLUB.getRoleList()){
            if(role.getName().equals(hPlayer.getRole().getName())){
                if (p.getLocation().distanceSquared(rika.getPlayer().getLocation()) < 20 * 20) {
                    if(rika.getPlayer().getGameMode() != GameMode.SPECTATOR){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, false));
                    }
                }
            }
        }

        if(hPlayer.getDeathTask() instanceof DeathTask){
           if(((DeathTask) hPlayer.getDeathTask()).isRunning()){
               System.out.println("dont move!");
               e.setCancelled(true);
           }else {
               e.setCancelled(false);
           }
        }

        Location blockLoc = e.getTo().getBlock().getRelative(BlockFace.DOWN).getLocation();


        if(((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).blockTraps.size() > 0){

            for(Location loc : ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).blockTraps){

                if(loc.getBlock().getLocation().equals(blockLoc)){
                    HPlayer satokoHojo =  RoleList.SATOKO_HOJO.getRole().getHPlayer();

                    if(satokoHojo == null || satokoHojo.getPlayer() == null || satokoHojo.getUuid().equals(p.getUniqueId())){
                        return;
                    }

                    satokoHojo.getPlayer().sendMessage(p.getName() + " est " + hPlayer.getRole().getName());
                    loc.getBlock().setType(Material.SOIL);
                    ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).blockTraps.remove(loc);
                    break;

                }
            }
        }



    }

}
