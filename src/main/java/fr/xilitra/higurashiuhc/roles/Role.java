package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;

public enum Role {

    RIKA_FURUDE(RikaFurude.class),
    MION_SONOZAKI(MionSonozaki.class),
    HANYU(Hanyu.class),
    SATOKO_HOJO(SatokoHojo.class),
    KEIICHI_MAEBARA(KeiichiMaebara.class),
    SHION_SONOSAKI(ShionSonozaki.class),
    RENA_RYUGU(RenaRyugu.class);

    private Class role;

    Role(Class role) {
        this.role = role;
    }

    public Class getRole() {
        return role;
    }
}
