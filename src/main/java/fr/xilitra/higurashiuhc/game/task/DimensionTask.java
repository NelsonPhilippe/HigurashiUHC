package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.TimerTask;

public class DimensionTask extends TimerTask {

    private int time = 10;//1200;
    private HPlayer rika;
    private HPlayer hanyu;
    private boolean teleportRika;

    public DimensionTask(HPlayer rika, HPlayer hanyu, boolean teleportRika) {
        this.rika = rika;
        this.hanyu = hanyu;
        this.teleportRika = teleportRika;
    }

    @Override
    public void run() {


        if(time <= 0){



            this.cancel();
        }

        time--;
    }
}
