package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum ScenarioList {
    DOLL(new Doll(), ConfigLocation.SCENARIO_DOLL),
    MISTREATMENT(new Mistreatment(), ConfigLocation.SCENARIO_MISTREATMENT),
    OYASHIRO(new Oyashiro(), ConfigLocation.SCENARIO_OYASHIRO);

    final Scenario scenario;
    final ConfigLocation configLocation;

    ScenarioList(Scenario scenario, ConfigLocation configLocation) {
        this.scenario = scenario;
        this.configLocation = configLocation;
    }

    private static List<ScenarioList> getActiveScenarioList() {
        List<ScenarioList> sl = new ArrayList<>();
        for (ScenarioList s : values())
            if (s.isActive())
                sl.add(s);
        return sl;
    }

    public static ScenarioList activateScenario() {

        List<ScenarioList> sla = getActiveScenarioList();
        if (sla.isEmpty()) {
            return null;
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

    public boolean isActive() {
        return HigurashiUHC.getGameManager().getConfigGestion().getConfig().getBoolean(configLocation);
    }

    public void setActive(boolean active) {
        HigurashiUHC.getGameManager().getConfigGestion().getConfig().set(configLocation, active);
        getScenario().scenarioStateChange(active);
    }
}
