package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebaraAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.MathMain;

public class RenaOyashiroTask extends JavaTask {

    @Override
    public void execute() {

        RenaRyuguAction rr = (RenaRyuguAction) Role.RENA_RYUGU.getRoleAction();
        KeiichiMaebaraAction km = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();

        if(rr.getLinkedRole().getHPlayer() == null || rr.getLinkedRole().getHPlayer().getPlayer() == null)
            return;

        float remove = 1;
        double diff = 20;

        if(km.getLinkedRole().getHPlayer() != null && km.getLinkedRole().getHPlayer().getPlayer() != null)
            diff = MathMain.calculDiff(rr.getLinkedRole().getHPlayer().getPlayer().getLocation(), km.getLinkedRole().getHPlayer().getPlayer().getLocation(), true);
        if(diff <= 15) remove += 1;

        ((Oyashiro) ScenarioList.OYASHIRO.getScenario()).addRenaProggress(remove*-1);

    }

}
