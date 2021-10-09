package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozakiAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class InverserCmd extends CommandsExecutor {

    public InverserCmd() {
        super("[Inverser]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 3) {
            Player firstTarget = Bukkit.getPlayer(strings[1]);
            Player secondsTarget = Bukkit.getPlayer(strings[2]);

            AkaneSonozakiAction akaneSonozakiAction = (AkaneSonozakiAction) hPlayer.getRole().getRoleAction();

            int time = p.getStatistic(Statistic.PLAY_ONE_TICK);

            int days = time / 20 / 60 / 60 / 24;

            if (akaneSonozakiAction.getNextDaySwap() < days) {
                p.sendMessage("Il est encore trop tot pour echanger les joueurs");
                return false;
            }

            Location loc1 = firstTarget.getLocation();
            Location loc2 = secondsTarget.getLocation();

            firstTarget.teleport(loc2);
            secondsTarget.teleport(loc1);

            akaneSonozakiAction.setNextDaySwap(days + 1);
            return true;
        }
        return false;
    }
}
