package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;

public class DimensionTask extends BukkitTask {

    private final HPlayer rika;
    private final HPlayer hanyu;
    private final boolean teleportRika;
    private int time = HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.ROLE_RIKA_HANYU_SECONDS);

    public DimensionTask(HPlayer rika, HPlayer hanyu, boolean teleportRika) {
        this.rika = rika;
        this.hanyu = hanyu;
        this.teleportRika = teleportRika;
    }

    @Override
    public void execute() {


        if (time <= 0) {


            this.stopTask();
        }

        time--;
    }
}
