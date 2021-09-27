package fr.xilitra.higurashiuhc.clans.hinamizawa;

import fr.xilitra.higurashiuhc.clans.Clans;

public class Sonozaki extends Clans {

    public static Sonozaki hinamizawa = new Sonozaki();

    public static Sonozaki getClans(){
        return hinamizawa;
    }

    public Sonozaki() {
        super("Sonozaki", Hinamizawa.getClans());
    }

    public enum roleList{

    }
}
