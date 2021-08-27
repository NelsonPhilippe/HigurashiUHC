package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.api.MariedReason;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.game.task.DeathTask;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class HPlayer {

    private String name;
    private UUID uuid;
    private Player player;
    private RoleList role;
    private Runnable deathTask;
    private Map<KuraudoOishi.infoList, String> info = new HashMap<>();
    private boolean playerDontMove;
    private boolean chatOkonogi;

    private HPlayer linkedToDeath = null;
    private HPlayer mariedWith = null;
    private MariedReason mariedReason = null;

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

    public RoleList getRoleList() {
        return role;
    }

    public void setRoleList(RoleList role){
        this.role = role;
        role.getRole().setPlayer(this);
    }

    public Clans getClans(){
        return ClansManager.getInstance().getClans(getRoleList().getRole());
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

    public boolean isChatOkonogi() {
        return chatOkonogi;
    }

    public void setChatOkonogi(boolean chatOkonogi) {
        this.chatOkonogi = chatOkonogi;
    }

    public void setLinkedToDeathWith(HPlayer roleList){
        linkedToDeath = roleList;
    }

    public HPlayer linkedToDeathWith(){
        return linkedToDeath;
    }

    public MariedReason getMariedReason(){
        return mariedReason;
    }

    public HPlayer getMarriedWith(){
        return mariedWith;
    }

    public void setMarriedWith(HPlayer roleList, MariedReason mr){
        mariedWith = roleList;
        this.mariedReason = mr;
    }

}
