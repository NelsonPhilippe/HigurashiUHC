package fr.xilitra.higurashiuhc.roles;

public enum Role {
    ;


    private Class role;

    Role(Class role) {
        this.role = role;
    }

    public Class getRole() {
        return role;
    }
}
