package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;

public class BanTaskExecutor extends TaskExecutor {

    @Override
    public void onExecute() {

        HPlayer oryo = Role.ORYO_SONOZAKI.getHPlayer();
        if (oryo == null) {
            return;
        }

        OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) oryo.getRole().getRoleAction();

        if (oryoSonozakiAction.getVotedPlayer().getPlayer() != null)
            oryoSonozakiAction.getVotedPlayer().getPlayer().setMaxHealth(oryoSonozakiAction.getVotedPlayer().getPlayer().getMaxHealth() + 5);

    }
}
