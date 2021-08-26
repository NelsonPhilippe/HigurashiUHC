package fr.xilitra.higurashiuhc.scenario;

public class ScenarioManager {

    private Scenario doll;

    public ScenarioManager() {
        initScenario();
    }

    private void initScenario(){
        doll = new Doll();
    }
}
