package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class MatraqueItem extends ItemBuilder{

    public static MatraqueItem matraqueItem = new MatraqueItem();

    public MatraqueItem() {
        super("Matraque", true, 1, Material.STICK);
        this.setLore(UUID.randomUUID().toString());
    }
}
