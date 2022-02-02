package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class StuntTaskExecutor extends TaskExecutor {

    private final HPlayer player;
    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.stunt");

    public StuntTaskExecutor(HPlayer player) {
        this.player = player;
    }

    @Override
    public void onExecute() {


        if (time == 0) {
            this.player.setPlayerDontMove(false);
            this.stopTask();
        }

        time--;
    }
}
