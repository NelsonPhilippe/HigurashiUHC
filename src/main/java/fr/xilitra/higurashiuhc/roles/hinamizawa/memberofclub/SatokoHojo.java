package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.traps.Traps;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SatokoHojo extends RoleTemplate implements Listener {
    public SatokoHojo() {
        super("SatokoHojo", Gender.FEMME);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player p = e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayers().get(p.getUniqueId());

        removeTraps(hPlayer);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e){

        if(!(e.getDamager() instanceof Snowball)) return;

        Snowball snowball = (Snowball) e.getDamager();

        if(!(snowball.getShooter() instanceof Player)) return;

        if(!(e.getEntity() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        Player victim = (Player) e.getEntity();

        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getPlayers().get(shooter.getUniqueId());
        HPlayer hPlayerVictim = HigurashiUHC.getGameManager().getPlayers().get(victim.getUniqueId());


        if(shooter.getItemInHand().getItemMeta().getLore().get(0).equals(Traps.slowBall.getLore())){

        }

    }

    public static void removeTraps(HPlayer hPlayer){
        if(hPlayer.getRole().getClass().equals(Role.SATOKO_HOJO.getRole())){
            Inventory inventory = hPlayer.getPlayer().getInventory();

            for(ItemStack item : inventory.getContents()){
                if(item.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())){
                    inventory.remove(item);
                }
            }
        }
    }
}
