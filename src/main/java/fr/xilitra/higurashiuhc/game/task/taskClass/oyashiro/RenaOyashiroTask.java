package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.MathMain;

public class RenaOyashiroTask extends JavaTask {

    @Override
    public void run() {

        RenaRyugu rr = (RenaRyugu) RoleList.RENA_RYUGU.getRole();
        KeiichiMaebara km = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();

        float remove = 1;
        double diff = MathMain.calculDiff(rr.getPlayer().getPlayer().getLocation(), km.getPlayer().getPlayer().getLocation(), true);
        if(diff <= 15) remove += 1;

        float newValue = rr.getBossBar().getProgress()-remove;
        if(newValue<=0){
            ScenarioList.OYASHIRO.getScenario().solution(3);
            return;
        }
        rr.getBossBar().setProgress(newValue);

    }

}
