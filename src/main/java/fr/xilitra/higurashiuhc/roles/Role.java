package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;

public enum Role {

    HINAMIZAWA(Hinamizawa.class);

    private Class role;

    Role(Class role) {
        this.role = role;
    }

    public Class getRole() {
        return role;
    }
}
