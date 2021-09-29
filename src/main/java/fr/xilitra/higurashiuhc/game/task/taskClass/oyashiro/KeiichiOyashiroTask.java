package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebaraAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;

public class KeiichiOyashiroTask extends JavaTask {

    @Override
    public void execute() {

        KeiichiMaebaraAction km = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();

        float newValue = km.getBossBar().getProgress()+1;
        if(newValue>=100){
            ScenarioList.OYASHIRO.getScenario().solution(1);
            return;
        }

        km.getBossBar().setProgress(newValue);

    }

}
