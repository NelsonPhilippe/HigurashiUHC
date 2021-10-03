package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class HoeTrap extends ItemBuilder {


    public HoeTrap() {
        super("HoeTrap", true, 1, Material.WOOD_HOE);
        this.getItemStack().setDurability((short) (59 - 3));
        this.setLore(UUID.randomUUID().toString());

    }


}
