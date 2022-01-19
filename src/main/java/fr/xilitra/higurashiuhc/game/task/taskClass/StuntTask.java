package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class StuntTask extends BukkitTask {

    private final HPlayer player;
    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.stunt");

    public StuntTask(HPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {


        if (time == 0) {
            this.player.setPlayerDontMove(false);
            this.stopTask();
        }

        time--;
    }
}
