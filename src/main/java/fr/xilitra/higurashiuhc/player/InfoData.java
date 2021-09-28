package fr.xilitra.higurashiuhc.player;

import java.util.HashMap;
import java.util.Map;

public class InfoData {

    private final Map<String, Object> dataInfos = new HashMap<>();

    public Map<String, Object> getDataInfos() {
        return dataInfos;
    }

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

    public enum InfoList {

        KILL("Nombre de kill"),
        DIAMOND("Nombre de diamand"),
        EFFECT("Effet sur le joueur"),
        COMMANDS("Commande accessible par le joueur"),
        APPLE("Nombre de pomme dorée"),
        ROLE("Role"),
        CLAN("Clan"),
        SPECIAL_ITEM("Item special possédé par le joueur"),
        SEXE("Sexe du joueur");

        String type;

        InfoList(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

}
