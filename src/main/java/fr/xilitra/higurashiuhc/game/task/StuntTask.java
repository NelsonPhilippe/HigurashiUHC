package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class StuntTask extends BukkitRunnable {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.stunt");
    private HPlayer player;

    public StuntTask(HPlayer player) {
        this.player = player;
    }

    @Override
    public void run() {


        if(time == 0){
            this.player.setPlayerDontMove(false);
            this.cancel();
        }

        time--;
    }
}
