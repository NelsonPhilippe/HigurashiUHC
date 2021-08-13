package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.scenario.Scenario;
import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
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

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){

        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if(inventory.getHolder().equals(ItemConfig.TRAGEDIES.getMenu())){
            if(item.isSimilar(Scenario.DOLL.getItem())){
                try {
                    HigurashiUHC.getGameManager().setScenario((Scenario) Scenario.DOLL.getScenario().newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
