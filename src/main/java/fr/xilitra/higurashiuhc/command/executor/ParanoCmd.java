package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import org.bukkit.entity.Player;

public class ParanoCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {

        Player p = hPlayer.getPlayer();
        if(p == null)
            return false;

        Oyashiro oyashiro = (Oyashiro) ScenarioList.OYASHIRO.getScenario();
        if(!oyashiro.isReveal())
            return false;

        oyashiro.paranoTask.runTaskTimer(60000,60000, 10);
        p.sendMessage("Keiichi est parano");

        return true;
    }
}
