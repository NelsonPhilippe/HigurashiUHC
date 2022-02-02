package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.ActionBar;
import org.bukkit.entity.Player;

public class KiichiroTaskExecutor extends TaskExecutor {

    final HPlayer hPlayer;

    public KiichiroTaskExecutor(HPlayer hPlayer) {
        this.hPlayer = hPlayer;
    }

    @Override
    public void onExecute() {

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

        if (hPlayer.getPlayer() == null) {
            stopTask();
            return;
        }

        ActionBar.sendActionBar(kiichiroPlayer, "Vie de " + hPlayer.getName() + ": " + hPlayer.getPlayer().getHealth());

    }
}
