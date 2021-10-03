package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class PolicierTask extends JavaTask {

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
