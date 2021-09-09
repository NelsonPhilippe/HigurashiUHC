package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.MathMain;

public class RenaOyashiroTask extends JavaTask {

    private int time = 50*60;

    @Override
    public void run() {

        RenaRyugu rr = (RenaRyugu) RoleList.RENA_RYUGU.getRole();
        KeiichiMaebara km = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();

        if(time == 0){
            ScenarioList.OYASHIRO.getScenario().solution(3);
        }else{

            if(time % 30 == 0){

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

        time--;
    }

}
