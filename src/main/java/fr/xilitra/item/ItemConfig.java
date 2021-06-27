package fr.xilitra.item;

import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.gui.config.TragediesConfig;

public enum ItemConfig {

    TRAGEDIES("Tragedies (Mode de jeu)", true, new TragediesConfig());

    String name;
    boolean enchantEffect;
    GuiMenu menu;

    ItemConfig(String name, boolean enchantEffect, GuiMenu menu){
        this.name = name;
        this.enchantEffect = enchantEffect;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public boolean isEnchantEffect() {
        return enchantEffect;
    }

    public GuiMenu getMenu() {
        return menu;
    }
}
