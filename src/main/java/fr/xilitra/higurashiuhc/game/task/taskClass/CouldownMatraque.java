package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;

public class CouldownMatraque extends JavaTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.matraque");

    public CouldownMatraque() {
        super("CouldownMatraque");
    }

    @Override
    public void run() {

        if(time == 0){
            MatraqueItem.matraqueItem.setUse(false);
            this.stopTask();
        }

        time--;

    }
}
