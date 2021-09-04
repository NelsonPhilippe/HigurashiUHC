package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.game.task.HanyuTaskInvisble;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class Hanyu extends Role implements Listener {

    private boolean isInvisible;
    private boolean dimensionIsUsed;
    private Map<HPlayer, Location> dimensionLastLoc = new HashMap<>();

    public Hanyu() {
        super("Hanyu", Gender.FEMME, MemberOfClub.getClans(), 1);
        this.dimensionIsUsed = false;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();

        HPlayer player = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(player.getRole().equals(RoleList.HANYU.getRole())){
            p.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(player.getUniqueId());

        if(e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;

        Entity entityDamager = e.getDamager();

        if(!(entityDamager instanceof Player)) return;

        Player damager = (Player) entityDamager;
        HPlayer hPlayerDamager = HigurashiUHC.getGameManager().getPlayer(damager.getUniqueId());



        if(hPlayer.getRole().isRole(RoleList.HANYU.getRole())){
            Hanyu hanyu = (Hanyu) hPlayer.getRole();


            if(hanyu.isInvisible){
                hanyu.setInvisible(false);

                for(Player players : Bukkit.getOnlinePlayers()){
                    players.showPlayer(player);
                    Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new HanyuTaskInvisble(), 20, 20);
                }
            }
        }


        if(hPlayerDamager.getRole().equals(RoleList.HANYU.getRole())){

            Hanyu hanyu = (Hanyu) hPlayer.getRole();
            if(hanyu.isInvisible){
                hanyu.setInvisible(false);

                for(Player players : Bukkit.getOnlinePlayers()){
                    players.showPlayer(player);
                    Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new HanyuTaskInvisble(), 20, 20);
                }
            }

        }


    }



    public void setInvisible(boolean invisible) {
        isInvisible = invisible;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public boolean isDimensionIsUsed() {
        return dimensionIsUsed;
    }

    public void setDimensionIsUsed(boolean dimensionIsUsed) {
        this.dimensionIsUsed = dimensionIsUsed;
    }

    public Map<HPlayer, Location> getDimensionLastLoc() {
        return dimensionLastLoc;
    }

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }
}
