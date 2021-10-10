package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.ActionBar;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MiyoTask extends BukkitTask {
    @Override
    public void execute() {
        Role takanoRole = Role.MIYO_TAKANO;
        HPlayer takanoHPlayer = takanoRole.getHPlayer();
        if (takanoHPlayer == null) {
            stopTask();
            return;
        }
        Player takanoPlayer = takanoHPlayer.getPlayer();
        if (takanoPlayer == null) {
            stopTask();
            return;
        }

        Role tomitakeRole = Role.JIRO_TOMITAKE;
        HPlayer tomitakeHPlayer = tomitakeRole.getHPlayer();
        if (tomitakeHPlayer == null) {
            stopTask();
            return;
        }
        Player tomitakePlayer = tomitakeHPlayer.getPlayer();
        if (tomitakePlayer == null) {
            stopTask();
            return;
        }

        Location location = tomitakePlayer.getLocation();

        ActionBar.sendActionBar(takanoPlayer, "Tomitake: world: " + location.getWorld().getName() + " x: " + location.getX() + " y: " + location.getY() + " z: " + location.getZ());

    }
}
