package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import org.bukkit.entity.Player;

public class ParanoCmd extends CommandsExecutor {

    public ParanoCmd() {
        super("[Parano]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        Oyashiro oyashiro = (Oyashiro) ScenarioList.OYASHIRO.getScenario();
        if (!oyashiro.isReveal())
            return false;

        oyashiro.paranoTask.runTaskTimer(60, 60, 10);
        p.sendMessage("Keiichi est parano");

        return true;
    }
}
