package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public abstract class TaskExecutor implements Task, Runnable {

    private final int taskID;
    BukkitTask bukkitTask = null;
    private int restExecute = -1;

    public TaskExecutor() {
        this.taskID = TaskReminder.instNum += 1;
    }

    @Override
    public boolean stopTask() {
        if (bukkitTask == null)
            return false;

        Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " stoppé, id: " + getTaskID());
        restExecute = 0;
        bukkitTask = null;
        TaskReminder.hash.remove(this.taskID);
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2) {
        return runTaskTimer(l1, l2, -1);
    }

    @Override
    public boolean runTaskTimer(long l1, long l2, int times) {
        if (isRunning())
            return false;

        if (times < 1)
            return false;

        restExecute = times;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, l1 * 20, l2 * 20);
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " lancé, cas 3, id: " + getTaskID());
        TaskReminder.hash.put(this.taskID, this);
        this.onStart();
        return true;
    }

    @Override
    public boolean runTaskLater(long l1) {
        return runTaskTimer(l1, l1, 1);
    }

    @Override
    public boolean isRunning() {
        return bukkitTask != null;
    }

    @Override
    public int getTaskID() {
        return taskID;
    }

    @Override
    public void run() {
        if (restExecute != -1) {
            restExecute--;
            if (restExecute == 0)
                this.stopTask();
        }

        this.onExecute();
    }

}
