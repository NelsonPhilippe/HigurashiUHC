package fr.xilitra.higurashiuhc.game.task;

import java.util.HashMap;
import java.util.Timer;

public class TaskRunner {

    protected static HashMap<Integer, Task> hash = new HashMap<>();
    protected static Timer timer = new Timer();
    protected static int instNum = 1;

    public static Task getTask(int taskID){

        return hash.get(taskID);

    }

}
