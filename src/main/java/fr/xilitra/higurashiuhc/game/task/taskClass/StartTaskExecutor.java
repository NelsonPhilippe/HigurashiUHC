package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;

public class StartTaskExecutor extends TaskExecutor {

    private int time = 10;

    @Override
    public void onExecute() {

        if (time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
            HigurashiUHC.getGameManager().getHPlayerList().values().forEach(
                    hPlayer -> TitlePacket.send(
                            hPlayer.getPlayer(),
                            1,
                            1,
                            1,
                            "La partie commence dans: ",
                            String.valueOf(time)
                    )
            );
            Bukkit.broadcastMessage("La partie commence dans " + time);

        }

        if (time == 0) {
            HigurashiUHC.getGameManager().game();

            this.stopTask();
        }

        time--;

    }


}
