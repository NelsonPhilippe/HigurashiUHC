package fr.xilitra.higurashiuhc.game.task;

public interface Task {
    boolean stopTask();

    boolean runTaskTimer(long l1, long l2);

    boolean runTaskTimer(long l1, long l2, int time);

    boolean runTaskLater(long l1);

    boolean isRunning();

    int getTaskID();

    void onExecute();

    default void onStart() {
    }
}
