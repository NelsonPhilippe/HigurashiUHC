package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class DimensionTask extends JavaTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.rika.hanyu");
    private HPlayer rika;
    private HPlayer hanyu;
    private boolean teleportRika;

    public DimensionTask(HPlayer rika, HPlayer hanyu, boolean teleportRika){
        super("dt-"+rika.getName()+hanyu.getName());
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
