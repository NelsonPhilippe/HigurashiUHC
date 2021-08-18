package fr.xilitra.higurashiuhc.item.config;

import fr.xilitra.higurashiuhc.item.ItemBuilder;
import org.bukkit.Material;

import java.util.UUID;

public class VillageItem extends ItemBuilder {

    public static VillageItem villageItem = new VillageItem();

    public VillageItem() {
        super("Village Item", true, 1, Material.WOOD);
        this.setLore(UUID.randomUUID().toString());
    }
}
