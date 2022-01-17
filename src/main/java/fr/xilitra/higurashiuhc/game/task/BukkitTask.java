package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import org.bukkit.Bukkit;

public abstract class BukkitTask implements Task, Runnable {

    private final int taskID;
    org.bukkit.scheduler.BukkitTask bukkitTask = null;
    boolean instant = false;
    private int restExecute = -1;

    public BukkitTask() {
        this.taskID = TaskRunner.instNum;
        TaskRunner.instNum += 1;
        TaskRunner.hash.put(this.taskID, this);
    }

    @Override
    public boolean stopTask() {
        if (bukkitTask == null)
            return false;

        Bukkit.getScheduler().cancelTask(getBukkitTaskID());
        restExecute = -1;
        bukkitTask = null;
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2) {
        if (isRunning())
            return false;

        instant = false;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, l1 * 20, l2 * 20);
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2, int times) {
        if (isRunning())
            return false;

        if (times <= 1)
            return false;

        instant = false;
        restExecute = times;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, l1 * 20, l2 * 20);
        return true;
    }

    @Override
    public boolean runTaskLater(long l1) {
        if (isRunning())
            return false;

        instant = true;
        bukkitTask = Bukkit.getScheduler().runTaskLater(HigurashiUHC.getInstance(), this, l1 * 20);
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

    public Integer getBukkitTaskID() {

        if (bukkitTask != null)
            return bukkitTask.getTaskId();

        return null;

    }

    @Override
    public void run() {
        this.execute();

        if (restExecute < 0)
            return;

        restExecute -= 1;
        if (!instant && restExecute <= 0)
            stopTask();
    }

    public abstract void execute();

}
