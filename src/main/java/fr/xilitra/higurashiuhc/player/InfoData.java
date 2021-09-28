package fr.xilitra.higurashiuhc.player;

import java.util.HashMap;
import java.util.Map;

public class InfoData {

    private final Map<String, Object> dataInfos = new HashMap<>();

    public void setDataInfo(String name, Object object){
        removeDataInfo(name);
        dataInfos.put(name, object);
    }

    public void removeDataInfo(String name){
        dataInfos.remove(name);
    }

    public boolean hasDataInfo(String name){
        return dataInfos.containsKey(name);
    }

    public Object getDataInfo(String name){
        return dataInfos.get(name);
    }

    public boolean getBoolean(String name){
        if(hasDataInfo(name))
            return (boolean) getDataInfo(name);
        return false;
    }

}
