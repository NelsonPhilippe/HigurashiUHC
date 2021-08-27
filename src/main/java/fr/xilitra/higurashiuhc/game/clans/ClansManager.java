package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;

import java.util.HashMap;
import java.util.UUID;

public class ClansManager {


    static ClansManager instance;
    public static ClansManager getInstance(){
        return instance;
    }

    public ClansManager(){
        instance = this;
    }


    private final HashMap<UUID, Clans> playerLink = new HashMap<>();

    public boolean hasClans(HPlayer player){
        return playerLink.containsKey(player.getUuid());
    }

    public Clans getClans(HPlayer player){
        return playerLink.get(player.getUuid());
    }

    public boolean removeClans(HPlayer player){
        if(!hasClans(player)) return false;
        getClans(player).removePlayer(player.getUuid());
        playerLink.remove(player.getUuid());
        return true;
    }

    public void setClans(HPlayer player, Clans clans){
        removeClans(player);
        playerLink.put(player.getUuid(), clans);
        clans.addPlayer(player.getUuid());
    }

}
