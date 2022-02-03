package fr.xilitra.higurashiuhc.game.task;

import java.util.Collection;
import java.util.HashMap;

public class TaskReminder {

    protected static HashMap<Integer, TaskExecutor> hash = new HashMap<>();
    protected static int instNum = 1;

    public static Task getTask(int taskID) {

        return hash.get(taskID);

    }

    public static Collection<TaskExecutor> getTaskList() {
        return hash.values();
    }

    public static void stopAllTask() {
        hash.values().forEach(Task::stopTask);
    }

}
