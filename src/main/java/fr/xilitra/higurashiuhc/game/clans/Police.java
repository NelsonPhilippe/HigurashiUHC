package fr.xilitra.higurashiuhc.game.clans;

public class Police extends Clans{

    public static Police hinamizawa = new Police();

    public static Police getClans(){
        return hinamizawa;
    }

    public Police() {
        super("Police");
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
