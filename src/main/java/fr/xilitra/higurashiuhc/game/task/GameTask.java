package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.utils.TimeUtils;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {

    private int time = 0;
    private int timePhase = HigurashiUHC.getInstance().getConfig().getInt("phase-time");

    @Override
    public void run() {

        String formatTime = TimeUtils.formatTime(time);


        time++;
    }
}
