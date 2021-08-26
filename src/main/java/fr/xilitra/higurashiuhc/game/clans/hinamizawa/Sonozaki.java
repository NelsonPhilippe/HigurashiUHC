package fr.xilitra.higurashiuhc.game.clans.hinamizawa;

import fr.xilitra.higurashiuhc.game.clans.Clans;

public class Sonozaki extends Clans {

    public static Sonozaki hinamizawa = new Sonozaki();

    public static Sonozaki getClans(){
        return hinamizawa;
    }

    public Sonozaki() {
        super("Sonozaki");
    }

    @Override
    public boolean isSubClans() {
        return true;
    }

    @Override
    public Clans getMajorClans() {
        return Hinamizawa.getClans();
    }

    public enum roleList{

    }
}
