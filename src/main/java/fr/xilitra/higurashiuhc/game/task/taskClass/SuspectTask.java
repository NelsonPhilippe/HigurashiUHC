package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;

public class SuspectTask extends BukkitTask {

    private int time;

    @Override
    public void run() {

        if(time == 0){
            HPlayer hPlayer =  Role.KURAUDO_OISHI.getHPlayer();

            if(hPlayer == null)
                return;

            KuraudoOishiAction kuraudoOishiAction = (KuraudoOishiAction) hPlayer.getRole().getRoleAction();

            HPlayer suspect = kuraudoOishiAction.getSuspect().get(kuraudoOishiAction.getSuspect().size() - 1);



            this.stopTask();
        }

        time--;
    }
}
