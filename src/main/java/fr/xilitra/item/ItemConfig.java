package fr.xilitra.item;

import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.gui.config.TragediesConfig;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ItemConfig {

    TRAGEDIES("Tragedies (Mode de jeu)", new TragediesItem("Tragedies", true, 1, Material.ANVIL).getItemStack(), new TragediesConfig("Tragedies", 9*3));

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
