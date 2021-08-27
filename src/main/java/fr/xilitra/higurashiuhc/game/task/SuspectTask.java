package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;

import java.util.TimerTask;

public class SuspectTask extends TimerTask {

    private int time;

    @Override
    public void run() {

        if(time == 0){
            HPlayer hPlayer =  RoleList.KURAUDO_OISHI.getRole().getPlayer();

            KuraudoOishi kuraudoOishi = (KuraudoOishi) hPlayer.getRole();

            HPlayer suspect = kuraudoOishi.getSuspect().get(kuraudoOishi.getSuspect().size() - 1);

            

            this.cancel();
        }

        time--;
    }
}
