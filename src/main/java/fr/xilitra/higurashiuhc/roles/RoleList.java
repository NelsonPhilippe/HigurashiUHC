package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozaki;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.Kasai;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.KiichiroKimiyoshi;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import fr.xilitra.higurashiuhc.roles.mercenaires.Mercenaire;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import fr.xilitra.higurashiuhc.roles.neutre.JiroTomitake;
import fr.xilitra.higurashiuhc.roles.neutre.SatoshiHojo;
import fr.xilitra.higurashiuhc.roles.police.Akasaka;
import fr.xilitra.higurashiuhc.roles.police.Kumagai;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.roles.police.Policier;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public enum RoleList {

    RIKA_FURUDE(new RikaFurude()),
    MION_SONOZAKI(new MionSonozaki()),
    HANYU(new Hanyu()),
    SATOKO_HOJO(new SatokoHojo()),
    KEIICHI_MAEBARA(new KeiichiMaebara()),
    SHION_SONOSAKI(new ShionSonozaki()),
    RENA_RYUGU(new RenaRyugu()),
    MIYO_TAKANO(new MiyoTakano()),
    SATOSHI_HOJO(new SatoshiHojo()),
    ORYO_SONOZAKI(new OryoSonozaki()),
    KASAI(new Kasai()),
    AKANE_SONOZAKI(new AkaneSonozaki()),
    KIICHIRO_KIMIYOSHI(new KiichiroKimiyoshi()),
    KURAUDO_OISHI(new KuraudoOishi()),
    KUMAGAI(new Kumagai()),
    AKASAKA(new Akasaka()),
    POLICIER(new Policier()),
    MERCENAIRE(new Mercenaire()),
    OKONOGI(new Okonogi()),
    JIRO_TOMITAKE(new JiroTomitake()),
    NULL(new NullRole());

    public static RoleList getRoleList(String role){
        for(RoleList roleList : values())
            if(roleList.getRole().getName().equals(role))
                return roleList;
        return null;
    }

    private final Role role;

    RoleList(Role role) {
        this.role = role;
        if(role != null)
        if(role instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) role, HigurashiUHC.getInstance());
    }

    public Role getRole() {
        return role;
    }

}
