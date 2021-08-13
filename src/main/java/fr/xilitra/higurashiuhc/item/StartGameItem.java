package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class StartGameItem extends ItemBuilder{

    public static StartGameItem startGameItem = new StartGameItem();

    public StartGameItem() {
        super("Start", true, 1, Material.GRASS);
        this.setLore(UUID.randomUUID().toString());
    }
}
