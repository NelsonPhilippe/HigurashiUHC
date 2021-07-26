package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.roles.neutre.SatoshiHojo;

public enum Role {

    RIKA_FURUDE(RikaFurude.class),
    MION_SONOZAKI(MionSonozaki.class),
    HANYU(Hanyu.class),
    SATOKO_HOJO(SatokoHojo.class),
    KEIICHI_MAEBARA(KeiichiMaebara.class),
    SHION_SONOSAKI(ShionSonozaki.class),
    RENA_RYUGU(RenaRyugu.class),
    MIYO_TAKANO(MiyoTakano.class),
    SATOSHI_HOJO(SatoshiHojo.class);

    private Class role;

    Role(Class role) {
        this.role = role;
    }

    public Class getRole() {
        return role;
    }
}
