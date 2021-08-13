package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.inventory.ItemStack;

public enum Scenario {
    DOLL(Doll.class, ItemConfig.DOLL.getItem()),
    MISTREATMENT(Mistreatment.class, null),
    OYASHIRO(Oyashiro.class, null);

    Class scenario;
    ItemStack item;

    Scenario(Class scenario, ItemStack item) {
        this.scenario = scenario;
        this.item = item;
    }

    public Class getScenario() {
        return scenario;
    }

    public ItemStack getItem() {
        return item;
    }
}
