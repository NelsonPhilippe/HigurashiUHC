package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;

public class Doll extends Scenario {

    private HigurashiUHC plugin;
    private GameManager gameManager;
    private Integer appliedSolution = null;

    public Doll() {
        super("Poupée");
        this.plugin = HigurashiUHC.getInstance();
        this.gameManager = HigurashiUHC.getGameManager();
    }

    @Override
    public void solution(int solutionN, Object... o) {
        this.appliedSolution = solutionN;
    }

    public Integer getAppliedSolution(){
        return appliedSolution;
    }

}
