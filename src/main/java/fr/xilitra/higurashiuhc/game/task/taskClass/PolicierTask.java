package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class PolicierTask extends BukkitTask {

    private final HPlayer hPlayer;
    private int time = 10 * 60;

    public PolicierTask(HPlayer hPlayer) {
        this.hPlayer = hPlayer;
    }

    @Override
    public void execute() {

        if (time == 0) {

            hPlayer.getInfoData().setDataInfo("PvIsUsed", false);
            this.stopTask();

        }
        time--;
    }
}
