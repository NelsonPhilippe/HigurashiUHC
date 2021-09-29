package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class DimensionTask extends BukkitTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.rika.hanyu");
    private HPlayer rika;
    private HPlayer hanyu;
    private boolean teleportRika;

    public DimensionTask(HPlayer rika, HPlayer hanyu, boolean teleportRika){
        this.rika = rika;
        this.hanyu = hanyu;
        this.teleportRika = teleportRika;
    }

    @Override
    public void execute() {


        if(time <= 0){



            this.stopTask();
        }

        time--;
    }
}
