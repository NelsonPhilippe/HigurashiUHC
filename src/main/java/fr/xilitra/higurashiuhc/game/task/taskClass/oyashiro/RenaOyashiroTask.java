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

        if(rr.getHPlayer() == null || rr.getHPlayer().getPlayer() == null)
            return;

        float remove = 1;
        double diff = 20;

        if(km.getHPlayer() != null && km.getHPlayer().getPlayer() != null)
            diff = MathMain.calculDiff(rr.getHPlayer().getPlayer().getLocation(), km.getHPlayer().getPlayer().getLocation(), true);
        if(diff <= 15) remove += 1;

        float newValue = rr.getBossBar().getProgress()-remove;
        if(newValue<=0){
            ScenarioList.OYASHIRO.getScenario().solution(3);
            return;
        }
        rr.getBossBar().setProgress(newValue);

    }

}
