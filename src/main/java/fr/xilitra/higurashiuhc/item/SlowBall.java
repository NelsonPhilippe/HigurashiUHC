package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class SlowBall extends ItemBuilder{
    public SlowBall() {
        super("SlowBall", true, 3, Material.SNOW_BALL);
        this.setLore(UUID.randomUUID().toString());
    }
}
