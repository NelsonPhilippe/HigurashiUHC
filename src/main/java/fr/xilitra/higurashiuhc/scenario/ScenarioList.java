package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.inventory.ItemStack;

public enum ScenarioList {
    DOLL(new Doll(), ItemConfig.DOLL.getItem(), false),
    MISTREATMENT(new Mistreatment(), null, false),
    OYASHIRO(new Oyashiro(), null, false);

    Scenario scenario;
    ItemStack item;
    boolean active;

    ScenarioList(Scenario scenario, ItemStack item, boolean active) {
        this.scenario = scenario;
        this.item = item;
        this.active = active;
    }

    public Scenario getScenario() {
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
        getScenario().scenarioStateChange(active);
    }
}
