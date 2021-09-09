package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;

public class SuspectTask extends JavaTask {

    private int time;

    public SuspectTask(){
        super("suspectTask");
    }

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
