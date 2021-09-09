package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;

public class BanTask extends BukkitTask {

    private int time = 60 * 10;

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
