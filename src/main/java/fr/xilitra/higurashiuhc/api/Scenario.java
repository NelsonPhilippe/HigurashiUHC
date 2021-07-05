package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Scenario {

    private String name;
    private List<Role> roles;
    private Player player;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
