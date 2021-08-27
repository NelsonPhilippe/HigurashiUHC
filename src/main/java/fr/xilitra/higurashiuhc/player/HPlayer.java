package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.game.task.DeathTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class HPlayer {

    private final String name;
    private final UUID uuid;
    private final Player player;
    private Role role;
    private final Runnable deathTask;
    private final Map<KuraudoOishi.infoList, String> info = new HashMap<>();
    private boolean playerDontMove;
    private boolean chatOkonogi;
    private int maledictionPower = 0;
    private PlayerState playerState = PlayerState.WAITING_ROLE;

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

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        if(this.role != null)
            this.role.removePlayer(this);
        this.role = role;
        role.addPlayer(this);
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

    public void setClans(Clans clans){
        ClansManager.getInstance().setClans(this, clans);
    }

    public Clans getClans(){
        return ClansManager.getInstance().getClans(this);
    }

    public boolean hasMalediction() {
        return maledictionPower != 0;
    }

    public void setMaledictionPower(int malediction) {
        this.maledictionPower = malediction;
    }

    public int getMaledictionPower(){
        return this.maledictionPower;
    }

    public void incrMalediction(){
        this.maledictionPower += 1;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }

    public void setPlayerState(PlayerState playerState){
        this.playerState = playerState;
    }

}
