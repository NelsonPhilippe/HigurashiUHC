package fr.xilitra.higurashiuhc.game.task;

import java.util.TimerTask;

public class BanTask extends TimerTask {

    private int time = 60 * 10;


    @Override
    public void run() {

        if(time == 0){
            if(VoteTask.banPlayer != null){
                VoteTask.banPlayer.getPlayer().setMaxHealth(20);
            }
            this.cancel();
        }

        time--;
    }
}
