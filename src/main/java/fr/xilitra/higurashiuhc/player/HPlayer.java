package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.task.DeathTask;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HPlayer {

    private String name;
    private UUID uuid;
    private Player player;
    private RoleTemplate role;
    private Runnable deathTask;
    private Map<KuraudoOishi.infoList, String> info = new HashMap<>();
    private boolean playerDontMove;

    public HPlayer(String name, UUID uuid, Player player) {
        this.name = name;
        this.uuid = uuid;
        this.player = player;
        this.deathTask = new DeathTask(player);
        this.playerDontMove = false;
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

    public RoleTemplate getRole() {
        return role;
    }

    public void setRole(RoleTemplate role){
        this.role = role;
    }

    public Runnable getDeathTask(){
        return this.deathTask;
    }

    public Map<KuraudoOishi.infoList, String> getInfo() {
        return info;
    }

    public boolean playerDontMove() {
        return playerDontMove;
    }

    public void setPlayerDontMove(boolean playerDontMove) {
        this.playerDontMove = playerDontMove;
    }
}
