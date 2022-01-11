package fr.xilitra.higurashiuhc.config.inv.item;

import fr.redline.invinteract.inv.holder.InventoryInfoHolder;
import fr.redline.invinteract.item.Item;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ScenItem extends Item {

    final ScenarioList scenarioList;

    public ScenItem(ScenarioList scenarioList){
        this.scenarioList = scenarioList;
    }

    @Override
    public ItemStack getItemStack(InventoryInfoHolder inventoryInfoHolder) {
        boolean active = scenarioList.isActive();
        ItemStack wool = new ItemStack(Material.WOOL, active? DyeColor.GREEN.getData() : DyeColor.RED.getData());
        ItemMeta itemMeta = wool.getItemMeta();
        itemMeta.setDisplayName("Scenario: " + scenarioList.getScenario().getName());
        List<String> lore = new ArrayList<String>(){{
            add("Scenario: " + scenarioList.getScenario().getName());
            add("Etat: " + (active ? ChatColor.GREEN + "Actif" : ChatColor.RED + "Inactif"));
            add("Clic Gauche: "+ChatColor.GREEN+"Actif");
            add("Clic Droit: "+ChatColor.RED+"Inactif");
        }};
        itemMeta.setLore(lore);
        wool.setItemMeta(itemMeta);
        return wool;
    }

    @Override
    public void onItemClicked(InventoryInfoHolder inventoryInfoHolder, InventoryClickEvent inventoryClickEvent) {
        if(inventoryClickEvent.isLeftClick())
            scenarioList.setActive(true);
        else if(inventoryClickEvent.isRightClick())
            scenarioList.setActive(false);
        inventoryInfoHolder.updatePage();
    }
}
