package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class FireCracker extends ItemBuilder {
    public FireCracker() {
        super("Pétard", true, 3, Material.TNT);
        this.setLore(UUID.randomUUID().toString());
    }
}
