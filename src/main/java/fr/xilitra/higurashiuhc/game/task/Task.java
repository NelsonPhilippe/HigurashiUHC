package fr.xilitra.higurashiuhc.game.task;

public interface Task {
    boolean stopTask();
    boolean runTask(long l1, long l2);
    boolean isRunning();
    int getTaskID();
}
