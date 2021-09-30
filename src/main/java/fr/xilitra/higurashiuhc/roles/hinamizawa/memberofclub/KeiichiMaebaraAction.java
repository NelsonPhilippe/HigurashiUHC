package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class KeiichiMaebaraAction extends RoleAction implements Listener {

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if(ScenarioList.OYASHIRO.isActive())
            ScenarioList.OYASHIRO.getScenario().solution(2);

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
