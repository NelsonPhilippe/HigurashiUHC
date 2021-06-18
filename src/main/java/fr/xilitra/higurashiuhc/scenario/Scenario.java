package fr.xilitra.higurashiuhc.scenario;

public enum Scenario {
    DOLL(Doll.class),
    MISTREATMENT(Mistreatment.class),
    OYASHIRO(Oyashiro.class);

    private Class scenario;

    Scenario(Class scenario) {
        this.scenario = scenario;
    }

    public Class getScenario() {
        return scenario;
    }
}
