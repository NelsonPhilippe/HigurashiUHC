package fr.xilitra.higurashiuhc.clans;

public class Neutre extends Clans{

    public static Neutre hinamizawa = new Neutre();

    public static Neutre getClans(){
        return hinamizawa;
    }

    public Neutre() {
        super("Neutre", null);
    }

}
