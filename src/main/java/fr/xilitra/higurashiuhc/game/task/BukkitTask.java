package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import org.bukkit.Bukkit;

public abstract class BukkitTask implements Task, Runnable {

    org.bukkit.scheduler.BukkitTask bukkitTask = null;
    boolean instant = false;
    private final int taskID;

    public BukkitTask() {
        this.taskID = TaskRunner.instNum;
        TaskRunner.instNum+=1;
        TaskRunner.hash.put(this.taskID, this);
    }

    @Override
    public boolean stopTask() {
        if(bukkitTask == null)
        return false;

        Bukkit.getScheduler().cancelTask(getBukkitTaskID());
        bukkitTask = null;
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2) {
        if(isRunning())
            return false;

        instant = false;
        bukkitTask = Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), this, (l1*20)/1000, (l2*20)/1000);
        return true;
    }

    @Override
    public boolean runTaskLater(long l1) {
        if(isRunning())
            return false;

        instant = true;
        bukkitTask = Bukkit.getScheduler().runTaskLater(HigurashiUHC.getInstance(), this, (l1*20)/1000);
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

    public Integer getBukkitTaskID(){

        if(bukkitTask != null)
            return bukkitTask.getTaskId();

        return null;

    }

    @Override
    public void run(){
        this.execute();
        if(instant)
            stopTask();
    }

    public abstract void execute();

}
