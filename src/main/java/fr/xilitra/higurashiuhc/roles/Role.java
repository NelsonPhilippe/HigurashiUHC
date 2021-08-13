package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozaki;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.Kasai;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.KiichiroKimiyoshi;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.roles.neutre.SatoshiHojo;
import fr.xilitra.higurashiuhc.roles.police.Akasaka;
import fr.xilitra.higurashiuhc.roles.police.Kumagai;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;

public enum Role {

    RIKA_FURUDE(RikaFurude.class),
    MION_SONOZAKI(MionSonozaki.class),
    HANYU(Hanyu.class),
    SATOKO_HOJO(SatokoHojo.class),
    KEIICHI_MAEBARA(KeiichiMaebara.class),
    SHION_SONOSAKI(ShionSonozaki.class),
    RENA_RYUGU(RenaRyugu.class),
    MIYO_TAKANO(MiyoTakano.class),
    SATOSHI_HOJO(SatoshiHojo.class),
    ORYO_SONOZAKI(OryoSonozaki.class),
    KASAI(Kasai.class),
    AKANE_SONOZAKI(AkaneSonozaki.class),
    KIICHIRO_KIMIYOSHI(KiichiroKimiyoshi.class),
    KURAUDO_OISHI(KuraudoOishi.class),
    KUMAGAI(Kumagai.class),
    AKASAKA(Akasaka.class);

    private Class role;

    Role(Class role) {
        this.role = role;
    }

    public Class getRole() {
        return role;
    }
}
