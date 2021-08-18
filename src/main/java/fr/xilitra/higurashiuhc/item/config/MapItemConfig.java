package fr.xilitra.higurashiuhc.item.config;

import fr.xilitra.higurashiuhc.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.UUID;

public class MapItemConfig extends ItemBuilder {

    public static MapItemConfig mapItemConfig = new MapItemConfig();
    public MapItemConfig() {
        super("Map Configuration", true, 1, Material.MAP);
        this.setLore(UUID.randomUUID().toString());
    }
}
