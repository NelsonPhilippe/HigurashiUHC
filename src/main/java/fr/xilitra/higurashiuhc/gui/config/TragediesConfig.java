package fr.xilitra.higurashiuhc.gui.config;

import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.item.ItemConfig;

public class TragediesConfig extends GuiMenu {
    public TragediesConfig() {
        super("Tragédie", 9);
        this.addItem(2, ItemConfig.DOLL.getItem());
        this.create();
    }

}
