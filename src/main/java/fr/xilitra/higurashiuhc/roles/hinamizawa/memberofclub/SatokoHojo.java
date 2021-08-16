package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.traps.Traps;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SatokoHojo extends RoleTemplate implements Listener {

    public static List<Location> blockTraps = new ArrayList<>();

    public SatokoHojo() {
        super("Satoko Hojo", Gender.FEMME);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player p = e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

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

        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getPlayer(shooter.getUniqueId());
        HPlayer hPlayerVictim = HigurashiUHC.getGameManager().getPlayer(victim.getUniqueId());


        if(shooter.getItemInHand().getItemMeta().getLore().get(0).equals(Traps.slowBall.getLore())){
            if(hPlayerShooter.getRole().getClass().equals(Role.SATOSHI_HOJO.getRole())){
                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 200, true));
            }
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());


        if(hPlayer.getRole() == null){
            return;
        }

        if(!(hPlayer.getRole().getClass().getName().equals(Role.SATOKO_HOJO.getRole().getName()))) return;

        ItemStack item = e.getItem();

        if(item.getItemMeta().getLore().get(0).equals(Traps.hoeTrap.getLore())){

            if(e.getClickedBlock().getType() != Material.GRASS || e.getClickedBlock().getType() != Material.DIRT) return;

            Location loc = e.getClickedBlock().getLocation();

            SatokoHojo.blockTraps.add(loc);

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){

        HPlayer player = e.getPlayer();

        if(player.getRole().getClass().getName().equalsIgnoreCase(Role.SATOKO_HOJO.getRole().getName())){

            Random random = new Random();

            int itemNumber = random.nextInt(3);

            ItemStack[] itemStacks = {Traps.slowBall.getItemStack(), Traps.hoeTrap.getItemStack(), Traps.fireCracker.getItemStack()};

            player.getPlayer().getInventory().addItem(itemStacks[itemNumber]);

        }

    }

    public static void removeTraps(HPlayer hPlayer){
        if(hPlayer.getRole().getClass().getName().equals(Role.SATOKO_HOJO.getRole().getName())){
            Inventory inventory = hPlayer.getPlayer().getInventory();

            for(ItemStack item : inventory.getContents()){
                if(item.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())){
                    inventory.remove(item);
                }
            }
        }
    }
}
