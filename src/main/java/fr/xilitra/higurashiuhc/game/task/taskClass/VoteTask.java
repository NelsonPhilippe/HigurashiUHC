package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;

import java.util.Map;

public class VoteTask extends BukkitTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.oryo.votetime");
    public static boolean isRunning = false;
    public static HPlayer banPlayer;

    @Override
    public void run() {

        isRunning = true;

        if(time == 0){
            HPlayer oryo =  RoleList.ORYO_SONOZAKI.getRole().getHPlayer();
            if(oryo == null) {
                stopTask();
                return;
            }

            OryoSonozaki oryoSonozaki = (OryoSonozaki) oryo.getRole();

            int majorite = 0;

            for(HPlayer player : HigurashiUHC.getGameManager().getPlayerList().values()){
                if(player.getClans().getName().equalsIgnoreCase("Hinamizawa")){
                    majorite++;
                }
            }

            for (Map.Entry<HPlayer, Integer> votesPlayer : oryoSonozaki.getVoteBan().entrySet()){
                if(votesPlayer.getValue() >= majorite / 2){
                    if(votesPlayer.getKey().getPlayer() != null)
                    votesPlayer.getKey().getPlayer().setMaxHealth(10);
                    banPlayer = votesPlayer.getKey();
                    new BanTask().runTask(1000,1000);
                    break;
                }
            }

            this.stopTask();
        }

        time--;
    }
}
