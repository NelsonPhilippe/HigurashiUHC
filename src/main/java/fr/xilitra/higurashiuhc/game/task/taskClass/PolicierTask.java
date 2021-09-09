package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.police.Policier;

public class PolicierTask extends JavaTask {

    private int time = 10 * 60;
    private final HPlayer hPlayer;

    public PolicierTask(HPlayer hPlayer){
        this.hPlayer = hPlayer;
    }

    @Override
    public void run() {

        if(time == 0){

            Policier policier = (Policier) hPlayer.getRole();

            policier.setPvIsUsed(false);
            this.stopTask();

        }
        time--;
    }
}
