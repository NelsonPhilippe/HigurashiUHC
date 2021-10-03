package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;

public class KeiichiOyashiroTask extends JavaTask {

    @Override
    public void execute() {

        float addValue = 1;

        Oyashiro oyashiro = (Oyashiro) ScenarioList.OYASHIRO.getScenario();
        if (oyashiro.paranoTask.isRunning())
            addValue += 2;

        oyashiro.addKeiichiProggress(addValue);

    }

}
