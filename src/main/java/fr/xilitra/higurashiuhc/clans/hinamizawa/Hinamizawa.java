package fr.xilitra.higurashiuhc.clans.hinamizawa;

import fr.xilitra.higurashiuhc.clans.Clans;

public class Hinamizawa extends Clans {

    public static Hinamizawa hinamizawa = new Hinamizawa();

    public static Hinamizawa getClans(){
        return hinamizawa;
    }

    public Hinamizawa() {
        super("Hinamizawa", null);
    }

}
