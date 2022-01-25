package fr.xilitra.higurashiuhc.roles;

public class NullAction implements RoleAction{
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Role getLinkedRole() {
        return Role.NULL;
    }
}
