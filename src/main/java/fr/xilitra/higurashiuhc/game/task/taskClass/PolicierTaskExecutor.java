package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class PolicierTaskExecutor extends TaskExecutor {

    private final HPlayer hPlayer;
    private int time = 10 * 60;

    public PolicierTaskExecutor(HPlayer hPlayer) {
        this.hPlayer = hPlayer;
    }

    @Override
    public void onExecute() {

        if (time == 0) {

            hPlayer.getInfoData().setDataInfo("PvIsUsed", false);
            this.stopTask();

        }
        time--;
    }
}
