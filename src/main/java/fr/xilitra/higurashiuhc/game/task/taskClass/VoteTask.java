package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;

public class VoteTask extends BukkitTask {

    private int time = HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.ROLE_ORYO_VOTE_SECONDS);

    @Override
    public void execute() {

        if (time == 0) {
            HPlayer oryo = Role.ORYO_SONOZAKI.getHPlayer();
            if (oryo == null) {
                stopTask();
                return;
            }

            OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) oryo.getRole().getRoleAction();

            int majorite = Clans.HINAMIZAWA.getHPlayerList().size();

            HPlayer votesPlayer = oryoSonozakiAction.getVotedPlayer();

            if (oryoSonozakiAction.getTotalVote() >= majorite / 2) {
                if (votesPlayer.getPlayer() != null) {
                    votesPlayer.getPlayer().setMaxHealth(votesPlayer.getPlayer().getMaxHealth() - 5);
                    votesPlayer.getPlayer().sendMessage("§4-\n" +
                            "§3Vous venez d’être banni du village d’§9Hinamizawa, §3vous perdez par conséquent 5 cœurs que vous récupérerez d’ici 10 minutes.\n" +
                            "§4-\n");
                }
                new BanTask().runTaskLater(10000);
            }

            this.stopTask();
        }

        time--;
    }
}
