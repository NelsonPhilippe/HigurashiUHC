package fr.xilitra.higurashiuhc.game.clans;

import org.bukkit.entity.Player;

public class Neutre extends Clans{

    public static Neutre hinamizawa = new Neutre();

    public static Neutre getClans(){
        return hinamizawa;
    }

    public Neutre() {
        super("Neutre");
    }

    @Override
    public boolean isSubClans() {
        return false;
    }

    @Override
    public Clans getMajorClans() {
        return null;
    }
}
