package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.VoteTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import org.bukkit.entity.Player;

public class VoteCmd implements CommandsExecutor {

    public static VoteTask voteTask = new VoteTask();

    @Override
    public boolean onCommand(HPlayer hPlayer, String[] args) {

        Player p = hPlayer.getPlayer();
        if (p == null) return false;

        if (!Clans.HINAMIZAWA.hisInClans(hPlayer, false)) {
            p.sendMessage("Tu ne peux pas voter");
            return true;
        }

        if (voteTask.isRunning()) {

            OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) Role.ORYO_SONOZAKI.getRoleAction();

            oryoSonozakiAction.addVote();
            p.sendMessage("Tu as voté");

            return true;

        }

        return false;
    }
}
