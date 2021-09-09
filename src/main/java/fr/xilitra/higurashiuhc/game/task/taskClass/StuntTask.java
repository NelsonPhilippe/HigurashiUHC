package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class StuntTask extends JavaTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.stunt");
    private final HPlayer player;

    public StuntTask(HPlayer player){
        this.player = player;
    }

    @Override
    public void run() {


        if(time == 0){
            this.player.setPlayerDontMove(false);
            this.stopTask();
        }

        time--;
    }
}
