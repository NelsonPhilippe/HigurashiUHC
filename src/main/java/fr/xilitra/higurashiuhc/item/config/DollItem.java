package fr.xilitra.higurashiuhc.item.config;

import fr.xilitra.higurashiuhc.item.ItemBuilder;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import org.bukkit.Material;

import java.util.UUID;

public class DollItem extends ItemBuilder {

    public static DollItem dollItem = new DollItem();

    public DollItem() {
        super("Poupée", true, 1, Material.LEATHER);
        this.setLore(UUID.randomUUID().toString());
    }
}
