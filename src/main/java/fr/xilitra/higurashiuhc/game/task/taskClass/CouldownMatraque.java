package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.item.MatraqueItem;

public class CouldownMatraque extends TaskExecutor {

    @Override
    public void onExecute() {

        MatraqueItem.matraqueItem.setUse(false);

    }
}
