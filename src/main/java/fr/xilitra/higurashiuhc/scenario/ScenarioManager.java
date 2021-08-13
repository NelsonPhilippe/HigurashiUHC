package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.api.Scenario;

public class ScenarioManager {

    private Scenario doll;

    public ScenarioManager() {
        initScenario();
    }

    private void initScenario(){
        doll = new Doll();
    }
}
