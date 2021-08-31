package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import org.bukkit.scheduler.BukkitRunnable;

public class CouldownMatraque extends BukkitRunnable {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.matraque");

    @Override
    public void run() {

        if(time == 0){
            MatraqueItem.matraqueItem.setUse(false);
            this.cancel();
        }

        time--;

    }
}
