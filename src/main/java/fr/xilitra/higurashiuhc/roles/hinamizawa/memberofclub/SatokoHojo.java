package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.traps.Traps;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SatokoHojo extends Role implements Listener {

    public List<Location> blockTraps = new ArrayList<>();

    public SatokoHojo() {
        super("Satoko Hojo", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e){

        if(!(e.getDamager() instanceof Snowball)) return;

        Snowball snowball = (Snowball) e.getDamager();

        if(!(snowball.getShooter() instanceof Player)) return;

        if(!(e.getEntity() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        Player victim = (Player) e.getEntity();

        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getHPlayer(shooter.getUniqueId());
        if(hPlayerShooter == null)
            return;
        HPlayer hPlayerVictim = HigurashiUHC.getGameManager().getHPlayer(victim.getUniqueId());

        System.out.println(snowball.getCustomName());

        if(snowball.getCustomName().equalsIgnoreCase(Traps.slowBall.getLore())){

            if(hPlayerShooter.getRole() != null && hPlayerShooter.getRole().equals(RoleList.SATOKO_HOJO.getRole())){

                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2, true));
            }
        }
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if(hPlayer == null)
            return;

        if(hPlayer.getRole() == null){
            return;
        }

        if(!(hPlayer.getRole().equals(RoleList.SATOKO_HOJO.getRole()))) return;


        if(p.getItemInHand() == null || !p.getItemInHand().getItemMeta().hasLore() || p.getItemInHand().getType() == Material.AIR) return;

        ItemStack item = e.getItem();


        if(item.getItemMeta().getLore().get(0).equals(Traps.hoeTrap.getLore())){

            Block block = e.getClickedBlock();
            Material type = block.getType();
            Location loc = block.getLocation();

            if(block.getType() == Material.DIRT || block.getType() == Material.GRASS || block.getType() == Material.SOIL){

                Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), () -> block.setType(type));

            }

            blockTraps.add(loc);

        }
    }

    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent e){

        if(!(e.getEntity() instanceof Snowball)) return;

        Snowball snowball = (Snowball) e.getEntity();

        if(!(snowball.getShooter() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getHPlayer(shooter.getUniqueId());
        if(hPlayerShooter == null)
            return;

        if(hPlayerShooter.getRole().equals(RoleList.SATOKO_HOJO.getRole())){


            snowball.setCustomName(Traps.slowBall.getLore());
            snowball.setCustomNameVisible(false);



        }

    }

    @EventHandler
    public void onRoleSelected(RoleSelected e){

        HPlayer player = e.getPlayer();
        if(player.getPlayer() == null)
            return;

        if(player.getRole().equals(RoleList.SATOKO_HOJO.getRole())){

            Random random = new Random();

            int itemNumber = random.nextInt(3);

            ItemStack[] itemStacks = {Traps.slowBall.getItemStack(), Traps.hoeTrap.getItemStack(), Traps.fireCracker.getItemStack()};

            player.getPlayer().getInventory().addItem(itemStacks[itemNumber]);

        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if(hPlayer == null)
            return;

        if(hPlayer.getRole().equals(RoleList.SATOKO_HOJO.getRole())){

            ItemStack itemStack = e.getItemInHand();

            if(!itemStack.getItemMeta().hasLore()) return;

            if(itemStack.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())){

                System.out.println(itemStack.getItemMeta().getDisplayName());
                itemStack.setAmount(itemStack.getAmount() - 1);
            }

        }

    }

    public void removeTraps(HPlayer hPlayer){
        if(hPlayer.getPlayer() == null)
            return;
        if(hPlayer.getRole().equals(RoleList.SATOKO_HOJO.getRole())){
            Inventory inventory = hPlayer.getPlayer().getInventory();

            for(ItemStack item : inventory.getContents()){
                if(item.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())){
                    inventory.remove(item);
                }
            }
        }
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

        removeTraps(killed);

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
