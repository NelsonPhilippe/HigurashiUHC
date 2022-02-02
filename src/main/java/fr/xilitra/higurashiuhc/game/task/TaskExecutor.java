package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import org.bukkit.Bukkit;

public abstract class TaskExecutor implements Task, Runnable {

    private final int taskID;
    org.bukkit.scheduler.BukkitTask bukkitTask = null;
    private int restExecute = -1;

    public TaskExecutor() {
        this.taskID = TaskReminder.instNum;
        TaskReminder.instNum += 1;
        TaskReminder.hash.put(this.taskID, this);
    }

    @Override
    public boolean stopTask() {
        if (bukkitTask == null)
            return false;

        Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " stoppé, id: " + getTaskID());
        restExecute = -1;
        bukkitTask = null;
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2) {
        if (isRunning())
            return false;

        restExecute = -1;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, l1 * 20, l2 * 20);
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " lancé, cas 2, bukkit id: " + getTaskID());
        this.onStart();
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2, int times) {
        if (isRunning())
            return false;

        if (times <= 1)
            return false;

        restExecute = times;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, l1 * 20, l2 * 20);
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " lancé, cas 3, bukkit id: " + getTaskID());
        this.onStart();
        return true;
    }

    @Override
    public boolean runTaskLater(long l1) {
        if (isRunning())
            return false;

        restExecute = -1;
        bukkitTask = Bukkit.getScheduler().runTaskLater(HigurashiUHC.getInstance(), this, l1 * 20);
        HigurashiUHC.getGameManager().log("Task: " + getClass().getName() + " lancé, cas 4, id: " + getTaskID());
        this.onStart();
        return true;
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
        this.onExecute();

        if(restExecute != -1){
            restExecute --;
            if(restExecute == 0)
                this.stopTask();
        }
    }

}
