package fr.xilitra.higurashiuhc.game.task;

import java.util.TimerTask;

public abstract class JavaTask extends TimerTask {

    public static JavaTask instance;
    public String taskInstance;
    private boolean running = false;
    private static int instNum = 1;

    public JavaTask(String taskInstance){
        instance = this;
        this.taskInstance = taskInstance+instNum;
        instNum+=1;
        TaskRunner.hash.put(this.taskInstance, this);
    }

    public String getTaskInstanceName(){
        return taskInstance;
    }

    public JavaTask getInstance(){
        return instance;
    }

    public boolean stopTask(){
        if(cancel()){
            running = false;
            return true;
        }
        return false;
    }

    public boolean runTask(long l1, long l2){
        if(running) return false;
        TaskRunner.timer.schedule(this, l1, l2);
        running = true;
        return true;
    }

    public boolean isRunning(){
        return running;
    }

    public void delete(){
        stopTask();
        TaskRunner.hash.remove(getTaskInstanceName());
    }

}
