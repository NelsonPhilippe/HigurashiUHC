package fr.xilitra.higurashiuhc.game.clans.hinamizawa;

import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;

public class MemberOfClub extends Clans {

    public static MemberOfClub hinamizawa = new MemberOfClub();

    public static MemberOfClub getClans(){
        return hinamizawa;
    }

    public MemberOfClub() {
        super("MemberOfClub");
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
        HANYU(Hanyu.class),
        KEIICHI_MAEBARA(KeiichiMaebara.class),
        MION_SONOZAKI(MionSonozaki.class),
        RENA_RYUGU(RenaRyugu.class),
        RIKA_FURUDE(RikaFurude.class),
        SATOKO_HOJO(SatokoHojo.class),
        SHION_SONOZAKI(ShionSonozaki.class);

        Class role;

        roleList(Class role) {
            this.role = role;
        }

        public Class getRole() {
            return role;
        }
    }

}
