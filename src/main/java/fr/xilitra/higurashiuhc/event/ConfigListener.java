package fr.xilitra.higurashiuhc.event;

import fr.xilitra.item.ItemConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ConfigListener implements Listener {

    @EventHandler
    public void onRightClickEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        ItemStack item = player.getItemInHand();

        if(item.isSimilar(ItemConfig.TRAGEDIES.getItem())){
            player.openInventory(ItemConfig.TRAGEDIES.getMenu().getInventory());
        }

    }

}
