package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.ActionBar;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class KiichiroTask extends BukkitTask {

    final HPlayer hPlayer;

    public KiichiroTask(HPlayer hPlayer){
        this.hPlayer = hPlayer;
    }

    @Override
    public void execute() {

        Role kiichiroRole = Role.KIICHIRO_KIMIYOSHI;
        HPlayer kiichiroHPlayer = kiichiroRole.getHPlayer();
        if (kiichiroHPlayer == null) {
            stopTask();
            return;
        }
        Player kiichiroPlayer = kiichiroHPlayer.getPlayer();
        if (kiichiroPlayer == null) {
            stopTask();
            return;
        }

        if(hPlayer.getPlayer() == null){
            stopTask();
            return;
        }

        ActionBar.sendActionBar(kiichiroPlayer, "Vie de " + hPlayer.getName() + ": " + hPlayer.getPlayer().getHealth());

    }
}
