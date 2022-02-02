package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.VoteTaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import org.bukkit.entity.Player;

public class VoteCmd extends CommandsExecutor {

    public static VoteTaskExecutor voteTask = new VoteTaskExecutor();

    public VoteCmd() {
        super("[Vote]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] args) {

        if (voteTask.isRunning()) {

            OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) Role.ORYO_SONOZAKI.getRoleAction();

            oryoSonozakiAction.addVote();
            sendOkay(p,"Tu as voté");

            return true;

        }

        return false;
    }
}
