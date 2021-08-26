package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.police.Policier;
import org.bukkit.scheduler.BukkitRunnable;

public class PolicierTask extends BukkitRunnable {

    private int time = 10 * 60;
    private HPlayer hPlayer;

    public PolicierTask(HPlayer hPlayer) {
        this.hPlayer = hPlayer;
    }

    @Override
    public void run() {

        if(time == 0){

            Policier policier = (Policier) hPlayer.getRoleList().getRole();

            policier.setPvIsUsed(false);
            this.cancel();
        }
        time--;
    }
}
