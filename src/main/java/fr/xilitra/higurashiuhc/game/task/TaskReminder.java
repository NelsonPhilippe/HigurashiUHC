package fr.xilitra.higurashiuhc.game.task;

import java.util.HashMap;

public class TaskReminder {

    protected static HashMap<Integer, Task> hash = new HashMap<>();
    protected static int instNum = 1;

    public static Task getTask(int taskID) {

        return hash.get(taskID);

    }

    public static void stopAllTask(){
        hash.values().forEach(Task::stopTask);
    }

}
