package fr.xilitra.higurashiuhc.game.task;

import java.util.TimerTask;

public abstract class JavaTask extends TimerTask implements Task{

    private boolean running = false;
    private boolean instant = false;
    private int restExecute = -1;
    private final int taskID;

    public JavaTask(){
        this.taskID = TaskRunner.instNum;
        TaskRunner.instNum+=1;
        TaskRunner.hash.put(this.taskID, this);
    }

    @Override
    public int getTaskID(){
        return taskID;
    }

    @Override
    public boolean stopTask(){
        running = false;
        restExecute = -1;
        return cancel();
    }

    @Override
    public boolean runTaskTimer(long l1, long l2){
        if(running) return false;
        TaskRunner.timer.schedule(this, l1, l2);
        running = true;
        instant = false;
        return true;
    }

    @Override
    public boolean runTaskTimer(long l1, long l2, int times){
        if(running) return false;
        if(times <= 1)
            return false;
        TaskRunner.timer.schedule(this, l1, l2);
        running = true;
        restExecute = times;
        instant = false;
        return true;
    }

    @Override
    public boolean runTaskLater(long l1){
        if(running) return false;
        TaskRunner.timer.schedule(this, l1);
        running = true;
        instant = true;
        return true;
    }

    @Override
    public boolean isRunning(){
        return running;
    }

    @Override
    public void run(){
        this.execute();

        if(restExecute < 0)
            return;

        restExecute -= 1;
        if(!instant && restExecute <= 0)
            stopTask();
    }

    public abstract void execute();

}
