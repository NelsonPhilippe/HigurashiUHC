package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;

public class BanTask extends JavaTask {

    private int time = 60 * 10;

    public BanTask() {
        super("banTask");
    }

    @Override
    public void run() {

        if(time == 0){
            if(VoteTask.banPlayer != null){
                VoteTask.banPlayer.getPlayer().setMaxHealth(20);
            }
            stopTask();
        }

        time--;
    }
}
