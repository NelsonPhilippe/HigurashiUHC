package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;

import java.util.Map;

public class VoteTask extends BukkitTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.oryo.votetime");
    public static boolean isRunning = false;
    public static HPlayer banPlayer;

    @Override
    public void run() {

        isRunning = true;

        if(time == 0){
            HPlayer oryo =  Role.ORYO_SONOZAKI.getHPlayer();
            if(oryo == null) {
                stopTask();
                return;
            }

            OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) oryo.getRole().getRoleAction();

            int majorite = 0;

            for(HPlayer player : HigurashiUHC.getGameManager().getHPlayerList().values()){
                if(player.getClans().getName().equalsIgnoreCase("Hinamizawa")){
                    majorite++;
                }
            }

            for (Map.Entry<HPlayer, Integer> votesPlayer : oryoSonozakiAction.getVoteBan().entrySet()){
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
