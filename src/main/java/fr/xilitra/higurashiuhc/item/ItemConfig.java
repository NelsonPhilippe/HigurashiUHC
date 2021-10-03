package fr.xilitra.higurashiuhc.item;

import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.gui.config.MapMenu;
import fr.xilitra.higurashiuhc.gui.config.TragediesConfig;
import fr.xilitra.higurashiuhc.gui.config.tragedies.DollItem;
import fr.xilitra.higurashiuhc.item.config.MapItemConfig;
import fr.xilitra.higurashiuhc.item.config.ScenarioItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemConfig {

    TRAGEDIES("Tragedies (Mode de jeu)", ScenarioItem.scenarioItem.getItemStack(), new TragediesConfig()),
    DOLL("Poupée", new DollItem("Poupée", true, 1, Material.SKULL).getItemStack(), null),
    MAP_CONFiG("Map Config", MapItemConfig.mapItemConfig.getItemStack(), new MapMenu());

    String name;
    ItemStack item;
    GuiMenu menu;

    ItemConfig(String name, ItemStack item, GuiMenu menu) {
        this.name = name;
        this.item = item;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public GuiMenu getMenu() {
        return menu;
    }

    public ItemStack getItem() {
        return item;
    }
}
