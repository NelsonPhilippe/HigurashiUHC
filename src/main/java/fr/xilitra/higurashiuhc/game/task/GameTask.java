package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.TimeUtils;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class GameTask extends BukkitRunnable {

    private int time = 0;
    private int decount = 3;


    @Override
    public void run() {

        String formatTime = TimeUtils.formatTime(time);

        for(Map.Entry<UUID, Scoreboard> scoreboard : HigurashiUHC.getScoreboardMap().entrySet()){
            if(time == 0 || time == 1 || time == 2 || time == 3){
                scoreboard.getValue().setLines(
                        "",
                        "La partie va commencer dans " + decount,
                        "",
                        "Vous êtes: " + HigurashiUHC.getGameManager().getPlayers().get(scoreboard.getKey()).getRole().getName(),
                        "",
                        "Episode: 0",
                        "",
                        "ip: 127.0.0.1"
                );

                decount--;
            }

            scoreboard.getValue().setLine(1, "Temps de jeu: " + formatTime);
        }

        int timePhase = HigurashiUHC.getInstance().getConfig().getInt("phase-time");

        if(time == timePhase){
            HigurashiUHC.getGameManager().setEpisode(HigurashiUHC.getGameManager().getEpisode());
        }
        time++;
    }
}
