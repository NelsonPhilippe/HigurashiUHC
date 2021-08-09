package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.TimerTask;

public class VoteTask extends TimerTask {

    private int time = 60 * 10;
    public static boolean isRunning = false;
    public static HPlayer banPlayer;

    @Override
    public void run() {

        isRunning = true;

        if(time == 0){
            HPlayer oryo = HigurashiUHC.getGameManager().getPlayerWithRole(Role.ORYO_SONOZAKI);
            OryoSonozaki oryoSonozaki = (OryoSonozaki) oryo.getRole();

            int majorite = 0;

            for(HPlayer player : HigurashiUHC.getGameManager().getPlayers().values()){
                if(player.getRole().getClan().getName().equalsIgnoreCase("Hinamizawa")){
                    majorite++;
                }
            }

            for (Map.Entry<HPlayer, Integer> votesPlayer : oryoSonozaki.getVoteBan().entrySet()){
                if(votesPlayer.getValue() >= majorite / 2){
                    votesPlayer.getKey().getPlayer().setMaxHealth(10);
                    banPlayer = votesPlayer.getKey();
                    Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new BanTask(), 20, 20);
                    break;
                }
            }

            this.cancel();
        }

        time--;
    }
}
