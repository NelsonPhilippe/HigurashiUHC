package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.roles.Role;

import java.util.ArrayList;
import java.util.List;

public abstract class Scenario {

    private String name;
    private List<Role> roles;

    public Scenario(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public abstract void solution();

    public String getName() {
        return name;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
