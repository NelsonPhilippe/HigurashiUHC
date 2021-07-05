package fr.xilitra.higurashiuhc.game.clans;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Clans {

    private String name;
    protected List<Player> players = new ArrayList<>();

    public Clans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }
}
