package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class MatraqueItem extends ItemBuilder {

    public static MatraqueItem matraqueItem = new MatraqueItem();

    private boolean used = false;

    public MatraqueItem() {
        super("Matraque", true, 1, Material.STICK);
        this.setLore(UUID.randomUUID().toString());
    }

    public boolean isUse() {
        return used;
    }

    public void setUse(boolean use) {
        this.used = use;
    }
}
