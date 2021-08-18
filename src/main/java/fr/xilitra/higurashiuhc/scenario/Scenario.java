package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.inventory.ItemStack;

public enum Scenario {
    DOLL(Doll.class, ItemConfig.DOLL.getItem(), false),
    MISTREATMENT(Mistreatment.class, null, false),
    OYASHIRO(Oyashiro.class, null, false);

    Class scenario;
    ItemStack item;
    boolean active;

    Scenario(Class scenario, ItemStack item, boolean active) {
        this.scenario = scenario;
        this.item = item;
        this.active = active;
    }

    public Class getScenario() {
        return scenario;
    }

    public ItemStack getItem() {
        return item;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
