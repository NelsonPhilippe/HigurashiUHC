package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.UUID;

public class StartTask extends JavaTask {

    private int time = 10;

    @Override
    public void execute() {

        for(Map.Entry<UUID, Scoreboard> scoreboard : HigurashiUHC.getScoreboardMap().entrySet()){
            scoreboard.getValue().setLines(
                    "",
                    ChatColor.GRAY + "Nombre de role : " + ChatColor.GOLD + Role.values().length,
                    "",
                    ChatColor.RED + "Titre de game",
                    "",
                    ChatColor.GRAY + "Teleportation dans : " + ChatColor.GOLD + time,
                    "",
                    ChatColor.DARK_PURPLE + "Okami Servers"
            );

            HigurashiUHC.addScoreboard(scoreboard.getKey(), scoreboard.getValue());
        }


        if (time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
            HigurashiUHC.getGameManager().getHPlayerList().values().forEach(
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
            Bukkit.broadcastMessage("La partie commence dans " + time);

        }

        if(time == 0){
            HigurashiUHC.getGameManager().game();

            this.stopTask();
        }

        time--;

    }


}
