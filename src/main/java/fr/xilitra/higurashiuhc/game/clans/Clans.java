package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Clans {

    private final String name;
    protected List<UUID> playerList = new ArrayList<>();

    public Clans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<HPlayer> getPlayerList() {

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>(){{
           for(UUID uuid : getPlayerListUUID())
               add(gm.getPlayer(uuid));
        }};

    }

    public List<UUID> getPlayerListUUID() {
        return playerList;
    }

    protected void addPlayer(UUID p){
        this.playerList.add(p);
    }

    protected void removePlayer(UUID p){
        this.playerList.remove(p);
    }

    public abstract boolean isSubClans();

    public abstract Clans getMajorClans();

    public boolean hisInClans(HPlayer player){

        Clans playerClans = player.getClans();

        if(playerClans.isSubClans()){

            return playerClans.getName().equals(getName()) || playerClans.getMajorClans().getName().equals(getName());

        }else return playerClans.getName().equals(getName());
    }

}
