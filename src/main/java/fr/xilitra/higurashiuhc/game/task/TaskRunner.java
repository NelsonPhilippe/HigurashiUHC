package fr.xilitra.higurashiuhc.game.task;

import java.util.HashMap;
import java.util.Timer;

public class TaskRunner {

    protected static HashMap<String, JavaTask> hash = new HashMap<>();
    protected static Timer timer = new Timer();

    public static JavaTask getTask(String taskInstanceName){

        return hash.get(taskInstanceName);

    }

}
