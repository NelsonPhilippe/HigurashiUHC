package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.traps.Traps;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e){
        ItemStack item = e.getItemInHand();
        Location loc = e.getBlock().getLocation();
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.getRole().getName().equals(RoleList.SATOKO_HOJO.getRole().getName())){
            if(item.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())){

                p.getWorld().getBlockAt(loc).setType(Material.AIR);

                TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);
                tnt.setIsIncendiary(true);
                tnt.setFuseTicks(60);
            }
        }
    }
}
