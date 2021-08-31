package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.TimerTask;

public class VoteTask extends TimerTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.oryo.votetime");
    public static boolean isRunning = false;
    public static HPlayer banPlayer;

    @Override
    public void run() {

        isRunning = true;

        if(time == 0){
            HPlayer oryo =  RoleList.ORYO_SONOZAKI.getRole().getPlayer();
            OryoSonozaki oryoSonozaki = (OryoSonozaki) oryo.getRole();

            int majorite = 0;

            for(HPlayer player : HigurashiUHC.getGameManager().getPlayerList().values()){
                if(player.getClans().getName().equalsIgnoreCase("Hinamizawa")){
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
