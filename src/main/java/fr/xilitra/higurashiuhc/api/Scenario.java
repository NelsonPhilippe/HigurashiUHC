package fr.xilitra.higurashiuhc.api;

public abstract class Scenario {

    private String name;

    public Scenario(String name) {
        this.name = name;
    }

    public abstract void solution(int solNumber, Object... objects); //(CDC)

    public String getName() {
        return name;
    }

}
