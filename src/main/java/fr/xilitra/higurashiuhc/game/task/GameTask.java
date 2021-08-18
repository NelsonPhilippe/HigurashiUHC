package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.utils.TimeUtils;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;

public class GameTask extends TimerTask {

    private int time = 0;
    private int decount = 3;
    private int timePhase = HigurashiUHC.getInstance().getConfig().getInt("phase-time") * 60;
    private int worldborderActivation = HigurashiUHC.getInstance().getConfig().getInt("activation-time");


    @Override
    public void run() {

        if(time >= worldborderActivation){
            World world = Bukkit.getWorld("world");

            WorldBorder border = world.getWorldBorder();
            double blockReduce = HigurashiUHC.getInstance().getConfig().getInt("wordborder-time-reduce");
            border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder") - blockReduce);
            HigurashiUHC.getGameManager().setWorldBorder(border.getSize());
        }
        
        String formatTime = TimeUtils.formatTime(time);
        int player_remaining = HigurashiUHC.getGameManager().getPlayers().size() - HigurashiUHC.getGameManager().getSpectator().size();

        for(Map.Entry<UUID, Scoreboard> scoreboard : HigurashiUHC.getScoreboardMap().entrySet()){
            scoreboard.getValue().setLines(
                    "",
                    ChatColor.GRAY + "Joueur restant : " + ChatColor.GOLD + player_remaining,
                    "",
                    ChatColor.RED + "Titre de game",
                    "",
                    ChatColor.GRAY + "Temps de la partie : " + ChatColor.GOLD + formatTime,
                    "",
                    ChatColor.GRAY + "Episode : " + ChatColor.GOLD + HigurashiUHC.getGameManager().getEpisode(),
                    "",
                    ChatColor.DARK_PURPLE + "Okami Servers"
            );

            HigurashiUHC.addScoreboard(scoreboard.getKey(), scoreboard.getValue());
            decount--;
        }

        if(HigurashiUHC.getGameManager().getEpisode() >= 6){
            HPlayer player = HigurashiUHC.getGameManager().getPlayerWithRole(Role.KURAUDO_OISHI);

            if(player != null){

                KuraudoOishi oishi = (KuraudoOishi) player.getRole();

                if(!oishi.isCoupableIsDesigned()){
                    player.getPlayer().setMaxHealth(2.5);
                }

            }

        }

        if(time == timePhase){
            timePhase = timePhase + timePhase;
            HigurashiUHC.getGameManager().setEpisode(HigurashiUHC.getGameManager().getEpisode() + 1);
            Bukkit.getServer().getPluginManager().callEvent(new EpisodeUpdate(HigurashiUHC.getGameManager().getEpisode()));
        }
        time++;
    }
}
