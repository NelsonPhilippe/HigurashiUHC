package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.game.task.DeathTask;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import org.bukkit.entity.Player;

import java.util.*;

public class HPlayer {

    private final String name;
    private final UUID uuid;
    private final Player player;
    private Role role = null;
    private Role killerRole = null;
    private final Runnable deathTask;
    private final Map<KuraudoOishi.infoList, String> info = new HashMap<>();
    private boolean playerDontMove;
    private boolean chatOkonogi;
    private int maledictionPower = 0;
    private final List<Reason> mrList = new ArrayList<>();
    private PlayerState playerState = PlayerState.WAITING_ROLE;

    private final Map<HPlayer, Reason> deathLinked = new HashMap<>();
    private final Map<HPlayer, Reason> maried = new HashMap<>();
    private boolean kit;
    private KitList kitList;

    private HPlayer linkedToDeath = null;
    private HPlayer mariedWith = null;
    private Reason mariedReason = null;

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



    public boolean hisDeathLinked(){
        return !deathLinked.isEmpty();
    }

    public Reason getDeathLinkReason(HPlayer hPlayer){
        return deathLinked.get(hPlayer);
    }

    public boolean hasDeathLinkReason(Reason reason){
        return deathLinked.containsValue(reason);
    }

    public HPlayer getDeathLinkPlayer(Reason reason){
        for(Map.Entry<HPlayer, Reason> map : deathLinked.entrySet())
            if(map.getValue().isReason(reason))
                return map.getKey();
        return null;
    }

    public List<HPlayer> getDeathLinkWith(){
        return new ArrayList<>(this.deathLinked.keySet());
    }

    public boolean addDeathLinkWith(HPlayer roleList, Reason r){
        if(this.deathLinked.containsKey(roleList) || this.deathLinked.containsValue(r))
            return false;
        this.deathLinked.put(roleList, r);
        return true;
    }

    public Reason removeDeathLink(HPlayer player){
        return this.deathLinked.remove(player);
    }

    public HPlayer removeDeathLink(Reason R){
        HPlayer mp = getDeathLinkPlayer(R);
        if(mp == null)
            return null;
        removeDeathLink(mp);
        return mp;
    }

    public boolean hisMarried(){
        return !maried.isEmpty();
    }

    public Reason getMariedReason(HPlayer hPlayer){
        return maried.get(hPlayer);
    }


    public boolean hasMariedReason(Reason reason){
        return maried.containsValue(reason);
    }

    public HPlayer getMariedPlayer(Reason reason){
        for(Map.Entry<HPlayer, Reason> map : maried.entrySet())
            if(map.getValue().isReason(reason))
                return map.getKey();
        return null;
    }

    public List<HPlayer> getMarriedWith(){
        return new ArrayList<>(this.maried.keySet());
    }

    public boolean addMarriedWith(HPlayer roleList, Reason r){
        if(this.maried.containsKey(roleList) || this.maried.containsValue(r))
            return false;
        this.maried.put(roleList, r);
        return true;
    }

    public Reason removeMarriedWith(HPlayer player){
        return this.maried.remove(player);
    }

    public HPlayer removeMarriedWith(Reason R){
        HPlayer mp = getMariedPlayer(R);
        if(mp == null)
            return null;
        removeMarriedWith(mp);
        return mp;
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

    public int getMaledictionPower(){
        return this.maledictionPower;
    }

    public void incrMalediction(Reason reason){
        this.maledictionPower += 1;
        addMaledictionReason(reason);
    }

    public void reduceMalediction(){
        if(this.maledictionPower != 0)
            this.maledictionPower -= 1;
    }

     public void addMaledictionReason(Reason mr){
        if(!hasMaledictionReason(mr))
        mrList.add(mr);
    }

    public void removeMaledictionReason(Reason mr){
        mrList.remove(mr);
    }

    public boolean hasMaledictionReason(Reason... mrList){
        for(Reason mr : mrList)
            if(this.mrList.contains(mr))
                return true;
        return false;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }

    public void setPlayerState(PlayerState playerState){
        this.playerState = playerState;
    }

    public void setKillerRole(Role role){
        killerRole = role;
    }

    public Role getKillerRole(){
        return killerRole;
    }

    public boolean hasKit() {
        return kit;
    }

    public void setKit(boolean kit) {
        this.kit = kit;
    }

    public KitList getKit() {
        return kitList;
    }

    public void setKit(KitList kitList) {
        this.kitList = kitList;
    }
}
