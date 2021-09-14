package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.MathMain;

public class KeiichiOyashiroTask extends JavaTask {

    @Override
    public void run() {

        KeiichiMaebara km = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();

        float newValue = km.getBossBar().getProgress()+1;
        if(newValue>=100){
            ScenarioList.OYASHIRO.getScenario().solution(1);
            return;
        }

        km.getBossBar().setProgress(newValue);

    }

}
