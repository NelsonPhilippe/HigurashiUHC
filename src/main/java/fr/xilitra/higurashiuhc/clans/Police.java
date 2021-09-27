package fr.xilitra.higurashiuhc.clans;

public class Police extends Clans{

    public static Police hinamizawa = new Police();

    public static Police getClans(){
        return hinamizawa;
    }

    public Police() {
        super("Police", null);
    }

}
