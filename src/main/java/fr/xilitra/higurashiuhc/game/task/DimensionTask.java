package fr.xilitra.higurashiuhc.game.task;

import org.bukkit.scheduler.BukkitRunnable;

public class DimensionTask extends BukkitRunnable {

    private int time = 1200;

    @Override
    public void run() {


        if(time <= 0){
            this.cancel();

        }

        time--;
    }
}
