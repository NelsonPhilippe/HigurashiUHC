package fr.xilitra.higurashiuhc.clans;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Clans {

    private final String name;
    protected final List<UUID> playerList = new ArrayList<>();
    protected final Clans majorClans;

    public Clans(String name, Clans majorClans) {
        this.name = name;
        this.majorClans = majorClans;
    }

    public String getName() {
        return name;
    }

    public List<HPlayer> getPlayerList() {

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>(){{
           for(UUID uuid : getPlayerListUUID())
               add(gm.getHPlayer(uuid));
        }};

    }

    public List<UUID> getPlayerListUUID() {
        return playerList;
    }

    public List<HPlayer> getOnlinePlayerList() {

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>(){{
            for(UUID uuid : getOnlinePlayerListUUID())
                add(gm.getHPlayer(uuid));
        }};

    }

    public List<UUID> getOnlinePlayerListUUID() {
        return new ArrayList<UUID>(){{
            for(UUID uuid : getPlayerListUUID()) {
                HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(uuid);
                if (hPlayer != null && hPlayer.getPlayer() != null)
                    add(uuid);
            }
        }};
    }

    protected void addPlayer(UUID p){
        this.playerList.add(p);
    }

    protected void removePlayer(UUID p){
        this.playerList.remove(p);
    }

    public boolean hisInClans(HPlayer player){
        return isClans(player.getClans());
    }

    public boolean isClans(Clans clans){
        return clans.getName().equals(getName());
    }

    public boolean isClans(Clans clans, boolean checkMajorClan){
        if(isClans(clans))
            return true;
        if(checkMajorClan && getMajorClans() != null)
            return getMajorClans().isClans(this);
        return false;
    }

    public boolean isSubClans(){
        return getMajorClans() != null;
    }

    public Clans getMajorClans(){
        return majorClans;
    }

}
