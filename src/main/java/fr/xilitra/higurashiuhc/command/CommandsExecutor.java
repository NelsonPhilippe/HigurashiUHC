package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.player.HPlayer;

public interface CommandsExecutor {

    boolean onCommand(HPlayer hPlayer, String[] strings);

}
