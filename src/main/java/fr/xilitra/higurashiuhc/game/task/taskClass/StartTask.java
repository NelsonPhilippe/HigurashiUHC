package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.UUID;

public class StartTask extends BukkitTask {

    private int time = 10;

    @Override
    public void execute() {

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
