package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;

public class CouldownMatraque extends BukkitTask {

    @Override
    public void execute() {

        MatraqueItem.matraqueItem.setUse(false);

    }
}
