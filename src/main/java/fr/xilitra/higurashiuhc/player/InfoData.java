package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.HigurashiUHC;

import java.util.HashMap;
import java.util.Map;

public class InfoData {

    private final Map<String, Object> dataInfos = new HashMap<>();
    private final HPlayer hPlayer;

    public InfoData(HPlayer hPlayer){
        this.hPlayer = hPlayer;
    }

    public Map<String, Object> getDataInfos() {
        return dataInfos;
    }

    public void setDataInfo(String name, Object object) {
        removeDataInfo(name);
        HigurashiUHC.getGameManager().log("INFO) Applying special data to player "+hPlayer.getName()+" Data name: "+name+" value: "+object);
        dataInfos.put(name, object);
    }

    public void removeDataInfo(String name) {
        HigurashiUHC.getGameManager().log("INFO) Removing special data to player "+hPlayer.getName()+" Data name: "+name);
        dataInfos.remove(name);
    }

    public boolean hasDataInfo(String name) {
        return dataInfos.containsKey(name);
    }

    public Object getDataInfo(String name) {
        Object info = dataInfos.get(name);
        if(info != null)
            HigurashiUHC.getGameManager().log("INFO) Player data: "+hPlayer.getName()+" getted data: "+name+" value: "+info);
        else HigurashiUHC.getGameManager().log("INFO) Player data: "+hPlayer.getName()+" getted data: "+name+" value: null");
        return info;
    }

    public boolean getBoolean(String name) {
        if (hasDataInfo(name))
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

        final String type;

        InfoList(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

}
