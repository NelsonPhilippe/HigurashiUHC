package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;

import java.util.TimerTask;

public abstract class JavaTask extends TimerTask implements Task {

    private final int taskID;
    private boolean running = false;
    private boolean instant = false;
    private int restExecute = -1;

    public JavaTask() {
        this.taskID = TaskRunner.instNum;
        TaskRunner.instNum += 1;
        TaskRunner.hash.put(this.taskID, this);
    }

    @Override
    public int getTaskID() {
        return taskID;
    }

    @Override
    public boolean stopTask() {
        running = false;
        restExecute = -1;
        return cancel();
    }

    @Override
    public boolean runTaskTimer(long l1, long l2) {
        if (running) return false;
        TaskRunner.timer.schedule(this, l1 * 1000L, l2 * 1000L);
        HigurashiUHC.getGameManager().log("Task: "+getClass().getName()+" lancé, cas 1, task id: "+getTaskID());
        running = true;
        instant = false;
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2, int times) {
        if (running) return false;
        if (times <= 1)
            return false;
        TaskRunner.timer.schedule(this, l1 * 1000L, l2 * 1000L);
        HigurashiUHC.getGameManager().log("Task: "+getClass().getName()+" lancé, cas 2, task id: "+getTaskID());
        running = true;
        restExecute = times;
        instant = false;
        return true;
    }

    @Override
    public boolean runTaskLater(long l1) {
        if (running) return false;
        TaskRunner.timer.schedule(this, l1 * 1000L);
        HigurashiUHC.getGameManager().log("Task: "+getClass().getName()+" lancé, cas 3, task id: "+getTaskID());
        running = true;
        instant = true;
        return true;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        this.execute();

        restExecute -= 1;
        if (!instant && restExecute <= 0)
            stopTask();
    }

    public abstract void execute();

}
