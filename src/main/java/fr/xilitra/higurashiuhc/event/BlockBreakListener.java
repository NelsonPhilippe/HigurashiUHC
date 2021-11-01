package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojoAction;
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
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();

        if (block.getType() != Material.DIRT || block.getType() != Material.GRASS) return;

        Location loc = block.getLocation();

        ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).blockTraps.remove(loc);

        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null)
            return;

        if (hPlayer.getRole().isRole(Role.SATOKO_HOJO)) {

            ItemStack item = e.getPlayer().getItemInHand();

            if (item == null || !item.getItemMeta().hasLore() || item.getType() == Material.AIR) return;

            if (item.getItemMeta().getLore().get(0).equalsIgnoreCase(Traps.hoeTrap.getLore())) {

                Material type = block.getType();

                if (block.getType() == Material.DIRT || block.getType() == Material.GRASS || block.getType() == Material.SOIL) {

                    Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), () -> block.setType(type));

                }

                ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).blockTraps.add(loc);

            }

        }

        if (block.getType() == Material.DIAMOND_ORE)
            hPlayer.getInfoData().setDataInfo(InfoData.InfoList.DIAMOND.name(), String.valueOf(Integer.parseInt((String) hPlayer.getInfoData().getDataInfo(InfoData.InfoList.DIAMOND.name())) + 1));
    }

}
