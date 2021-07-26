package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class BaseballBat extends ItemBuilder{
    public BaseballBat() {
        super("Batte de Baseball", true, 1, Material.WOOD_SWORD);
        this.setLore(UUID.randomUUID().toString());
        this.getItemStack().setDurability((short) 3);
    }

}
