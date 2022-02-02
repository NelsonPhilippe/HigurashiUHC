package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.hanyu.DimensionTaskTP;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurudeAction;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DimensionDeathCmd extends CommandsExecutor {

    public DimensionDeathCmd() {
        super("[Dimension]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] args) {

        RikaFurudeAction rfa = (RikaFurudeAction) Role.RIKA_FURUDE.getRoleAction();

        if (!rfa.secondsCounterTaskExecutor.active) {
            sendError(p, "Rika n'a pas perdue de vie récamment");
        }

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if (rika == null) {
            sendError(p, "Rika not found");
            return false;
        }

        Player player = rika.getPlayer();
        if (player == null) {
            sendError(p, "Rika not found");
            return false;
        }

        World world = Bukkit.getWorld("world");
        World dimension = Bukkit.getWorld("dimension");

        new DimensionTaskTP(p, player, dimension, world, 120);

        return true;
    }
}
