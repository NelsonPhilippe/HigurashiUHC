package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

public abstract class Scenario {

    private String name;

    public Scenario(String name) {
        this.name = name;
    }

    public abstract void solution();

    public String getName() {
        return name;
    }
}
