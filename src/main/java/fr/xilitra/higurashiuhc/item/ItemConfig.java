package fr.xilitra.higurashiuhc.item;

import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.gui.config.MapMenu;
import fr.xilitra.higurashiuhc.gui.config.TragediesConfig;
import fr.xilitra.higurashiuhc.gui.config.tragedies.DollItem;
import fr.xilitra.higurashiuhc.item.config.MapItemConfig;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemConfig {

    TRAGEDIES("Tragedies (Mode de jeu)", new ItemBuilder("Tragedies", true, 1, Material.ANVIL).getItemStack(), new TragediesConfig("Tragedies", 9*3)),
    DOLL("Poupée", new DollItem("Poupée", true, 1, Material.SKULL).getItemStack(), null),
    MAP_CONFiG("Map Config", new MapItemConfig().getItemStack(), new MapMenu());

    String name;
    ItemStack item;
    GuiMenu menu;

    ItemConfig(String name, ItemStack item, GuiMenu menu){
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
