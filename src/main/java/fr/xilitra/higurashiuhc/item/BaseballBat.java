package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class BaseballBat extends ItemBuilder {

    private boolean isCrafted;
    private int used = 3;
    private boolean isUsedOnEpisode = false;

    public BaseballBat() {
        super("Batte de Baseball", true, 1, Material.WOOD_SWORD);
        this.setLore(UUID.randomUUID().toString());
        this.getItemStack().setDurability((short) 3);
        this.isCrafted = false;
    }

    public boolean isCrafted() {
        return isCrafted;
    }

    public void setCrafted(boolean crafted) {
        isCrafted = crafted;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public boolean isUsedOnEpisode() {
        return isUsedOnEpisode;
    }

    public void setUsedOnEpisode(boolean usedOnEpisode) {
        isUsedOnEpisode = usedOnEpisode;
    }
}
