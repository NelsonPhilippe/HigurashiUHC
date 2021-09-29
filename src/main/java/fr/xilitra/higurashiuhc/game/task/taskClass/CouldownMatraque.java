package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;

public class CouldownMatraque extends JavaTask {

    @Override
    public void execute() {

            MatraqueItem.matraqueItem.setUse(false);

    }
}
