package fr.xilitra.higurashiuhc.game.clans;

import org.bukkit.entity.Player;

public class Mercenaire extends Clans{
    public Mercenaire(String name) {
        super(name);
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void removePlayer(Player p){
        this.players.remove(p);
    }
}
