package fr.xilitra.higurashiuhc.game.task;

import java.util.TimerTask;

public abstract class JavaTask extends TimerTask implements Task{

    private boolean running = false;
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
        if(cancel()){
            running = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean runTask(long l1, long l2){
        if(running) return false;
        TaskRunner.timer.schedule(this, l1, l2);
        running = true;
        return true;
    }

    @Override
    public boolean isRunning(){
        return running;
    }

}
