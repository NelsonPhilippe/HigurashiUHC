package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;

public class MercenaireClan extends Clans{

    public static MercenaireClan hinamizawa = new MercenaireClan();

    public static MercenaireClan getClans(){
        return hinamizawa;
    }

    public MercenaireClan() {
        super("Mercenaire");
    }

    @Override
    public boolean isSubClans() {
        return false;
    }

    @Override
    public Clans getMajorClans() {
        return null;
    }

    public enum roleList{
        MERCENAIRE(fr.xilitra.higurashiuhc.roles.mercenaires.Mercenaire.class),
        MIYO_TANAKO(MiyoTakano.class),
        OKONOJI(Okonogi.class);

        Class role;

        roleList(Class role){
            this.role = role;
        }

        public Class getRole() {
            return role;
        }
    }
}
