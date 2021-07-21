package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import org.bukkit.scheduler.BukkitRunnable;

public class RikaDeathTask extends BukkitRunnable {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("rika-death-time");

    @Override
    public void run() {

        if(time == 0){
            this.cancel();
        }

        time--;
    }
}
