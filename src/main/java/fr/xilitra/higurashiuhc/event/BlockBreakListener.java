package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();

        if(block.getType() != Material.DIRT || block.getType() != Material.GRASS) return;

        Location loc = block.getLocation();

        if(SatokoHojo.blockTraps.contains(loc)){

            SatokoHojo.blockTraps.remove(loc);

        }
    }

}
