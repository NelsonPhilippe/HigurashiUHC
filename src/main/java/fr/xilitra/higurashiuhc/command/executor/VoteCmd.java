package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.VoteTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import org.bukkit.entity.Player;

public class VoteCmd extends CommandsExecutor {

    public static VoteTask voteTask = new VoteTask();

    public VoteCmd() {
        super("[Vote]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] args) {

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
