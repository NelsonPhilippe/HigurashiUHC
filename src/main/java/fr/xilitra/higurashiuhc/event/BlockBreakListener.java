package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.traps.Traps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();

        if(block.getType() != Material.DIRT || block.getType() != Material.GRASS) return;

        Location loc = block.getLocation();

        ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).blockTraps.remove(loc);

        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.getRoleList().getRole().getName().equalsIgnoreCase("Satoko Hojo")){

            ItemStack item = e.getPlayer().getItemInHand();

            if(item == null || !item.getItemMeta().hasLore() || item.getType() == Material.AIR) return;

            if(item.getItemMeta().getLore().get(0).equalsIgnoreCase(Traps.hoeTrap.getLore())){

                Material type = block.getType();

                if(block.getType() == Material.DIRT || block.getType() == Material.GRASS || block.getType() == Material.SOIL){

                    Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            block.setType(type);
                        }
                    });

                }

                ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).blockTraps.add(loc);

            }

        }

        if(block.getType() == Material.DIAMOND_ORE){
            int diamond =Integer.parseInt(hPlayer.getInfo().get(KuraudoOishi.infoList.DIAMOND));

            hPlayer.getInfo().put(KuraudoOishi.infoList.DIAMOND, String.valueOf(diamond + 1));
        }
    }

}
