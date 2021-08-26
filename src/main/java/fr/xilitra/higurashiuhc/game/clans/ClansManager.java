package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.api.Role;

import java.util.HashMap;

public class ClansManager {


    static ClansManager instance;
    public static ClansManager getInstance(){
        return instance;
    }

    public ClansManager(){
        instance = this;
    }


    private final HashMap<Role, Clans> playerLink = new HashMap<>();

    public boolean hasClans(Role player){
        return playerLink.containsKey(player);
    }

    public Clans getClans(Role player){
        return playerLink.get(player);
    }

    public boolean removeClans(Role player){
        if(!hasClans(player)) return false;
        getClans(player).removeRole(player);
        playerLink.remove(player);
        return true;
    }

    public void setClans(Role player, Clans clans){
        removeClans(player);
        playerLink.put(player, clans);
        clans.addRole(player);
    }

}
