package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.gui.config.MapMenu;
import fr.xilitra.higurashiuhc.item.ItemConfig;
import fr.xilitra.higurashiuhc.item.config.MapItemConfig;
import fr.xilitra.higurashiuhc.item.config.ScenarioItem;
import fr.xilitra.higurashiuhc.item.config.StartGameItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(p.isOp()){
            ItemStack item = e.getPlayer().getInventory().getItemInHand();

            if (p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null || !p.getItemInHand().getItemMeta().hasLore()) {
                return;
            }
            String lore = item.getItemMeta().getLore().get(0);


            if(lore.equals(StartGameItem.startGameItem.getLore())){
                HigurashiUHC.getGameManager().start();
                e.setCancelled(true);
                return;
            }

            if(item.isSimilar(ItemConfig.MAP_CONFiG.getItem())){
                p.openInventory(MapMenu.mapMenu.getInventory());
                e.setCancelled(true);
                return;
            }

            if(item.isSimilar(ItemConfig.TRAGEDIES.getItem())){
                p.openInventory(ItemConfig.TRAGEDIES.getMenu().getInventory());
                e.setCancelled(true);
                return;
            }

        }

    }

}
