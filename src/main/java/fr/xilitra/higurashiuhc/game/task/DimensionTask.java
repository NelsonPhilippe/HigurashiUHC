package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;

import java.util.TimerTask;

public class DimensionTask extends TimerTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.rika.hanyu");
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
