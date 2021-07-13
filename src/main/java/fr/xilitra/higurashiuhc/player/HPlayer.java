package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HPlayer {

    private String name;
    private UUID uuid;
    private Player player;
    private Role role;

    public HPlayer(String name, UUID uuid, Player player) {
        this.name = name;
        this.uuid = uuid;
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
