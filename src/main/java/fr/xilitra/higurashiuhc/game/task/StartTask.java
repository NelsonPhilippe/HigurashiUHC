package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class StartTask extends BukkitRunnable {

    private int time = 60;

    @Override
    public void run() {

        if (time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
            HigurashiUHC.getGameManager().getPlayers().values().forEach(
                    player -> TitlePacket.send(
                            Bukkit.getPlayer(
                                    player.getUuid()),
                            1,
                            1,
                            1,
                            "La partie commence dans: ",
                            String.valueOf(time)
                    )
            );
        }

        if(time != 0){
            this.cancel();
        }

    }


}
