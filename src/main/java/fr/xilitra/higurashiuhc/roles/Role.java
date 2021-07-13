package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.MionSonozaki;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurude;

public enum Role {

    RIKA_FURUDE(new RikaFurude()),
    MION_SONOZAKI(new MionSonozaki());

    private RoleTemplate role;

    Role(RoleTemplate role) {
        this.role = role;
    }

    public RoleTemplate getRole() {
        return role;
    }
}
