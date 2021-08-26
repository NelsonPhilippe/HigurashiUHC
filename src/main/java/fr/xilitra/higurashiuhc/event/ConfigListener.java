package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ConfigListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){

        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if(inventory.getHolder().equals(ItemConfig.TRAGEDIES.getMenu())){
            if(item.isSimilar(ScenarioList.DOLL.getItem())){
                if(ScenarioList.DOLL.isActive()){
                    ScenarioList.DOLL.setActive(false);
                    return;
                }
                ScenarioList.DOLL.setActive(true);

            }
        }
    }

}
