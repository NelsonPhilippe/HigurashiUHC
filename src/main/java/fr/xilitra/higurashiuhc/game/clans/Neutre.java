package fr.xilitra.higurashiuhc.game.clans;

import org.bukkit.entity.Player;

public class Neutre extends Clans{
    public Neutre(String name) {
        super(name);
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void removePlayer(Player p){
        this.players.remove(p);
    }
}
