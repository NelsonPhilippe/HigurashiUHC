package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.item.MatraqueItem;
import org.bukkit.scheduler.BukkitRunnable;

public class CouldownMatraque extends BukkitRunnable {

    private int time = 5 * 60;

    @Override
    public void run() {

        if(time == 0){
            MatraqueItem.matraqueItem.setUse(false);
            this.cancel();
        }

        time--;

    }
}
