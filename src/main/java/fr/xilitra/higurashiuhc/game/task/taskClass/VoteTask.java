package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;

public class VoteTask extends BukkitTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.oryo.votetime");

    @Override
    public void execute() {

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

            HPlayer votesPlayer = oryoSonozakiAction.getVotedPlayer();

            if(oryoSonozakiAction.getTotalVote() >= majorite / 2){
                if(votesPlayer.getPlayer() != null)
                    votesPlayer.getPlayer().setMaxHealth(votesPlayer.getPlayer().getMaxHealth()-5);
                new BanTask().runTaskLater(10000);
            }

            this.stopTask();
        }

        time--;
    }
}
