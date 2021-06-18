package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.game.GameStates;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class StartTask extends BukkitRunnable {

    private int time = 300;

    @Override
    public void run() {

        if (time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {

        }

        if(time != 0){
            this.cancel();
        }

    }


}
