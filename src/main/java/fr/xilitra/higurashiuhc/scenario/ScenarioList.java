package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.item.ItemConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static List<ScenarioList> getActiveScenarioList() {
        List<ScenarioList> sl = new ArrayList<>();
        for (ScenarioList s : values())
            if (s.isActive())
                sl.add(s);
        return sl;
    }

    public static ScenarioList getActiveScenario() {
        for (ScenarioList s : values())
            if (s.isActive())
                return s;
        return null;
    }

    public static ScenarioList activateScenario() {

        List<ScenarioList> sla = getActiveScenarioList();
        if (sla.size() <= 1) {
            return sla.get(0);
        }

        Random rand = new Random();
        int random_integer = rand.nextInt(sla.size() - 1);

        ScenarioList actScenario = sla.get(random_integer);

        sla.remove(actScenario);

        for (ScenarioList sl : sla)
            sl.setActive(false);

        if (actScenario.getScenario() instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) actScenario.getScenario(), HigurashiUHC.getInstance());

        return actScenario;

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
