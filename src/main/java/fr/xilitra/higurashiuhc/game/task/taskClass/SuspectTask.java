package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;

public class SuspectTask extends BukkitTask {

    private int time;

    @Override
    public void run() {

        if(time == 0){
            HPlayer hPlayer =  RoleList.KURAUDO_OISHI.getRole().getHPlayer();

            if(hPlayer == null)
                return;

            KuraudoOishi kuraudoOishi = (KuraudoOishi) hPlayer.getRole();

            HPlayer suspect = kuraudoOishi.getSuspect().get(kuraudoOishi.getSuspect().size() - 1);



            this.stopTask();
        }

        time--;
    }
}
