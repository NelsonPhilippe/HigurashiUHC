package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTask;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import javax.sound.sampled.Line;
import java.util.*;

public class HPlayer {

    private final String name;
    private final UUID uuid;

    private Role role = Role.NULL;
    private Role roleKiller = Role.NULL;

    private Entity killer = null;
    private final DeathTask deathTask;

    private boolean playerDontMove = false;
    private boolean chatOkonogi = false;
    private boolean kit = false;

    private PlayerState playerState = PlayerState.WAITING_ROLE;
    private KitList kitList = null;

    private final Map<HPlayer, LinkData> linkData = new HashMap<>();
    private final List<Reason> mrList = new ArrayList<>();
    private final InfoData infoData = new InfoData();

    public HPlayer(String name, Player player) {
        this.name = name;
        this.uuid = player.getUniqueId();
        this.deathTask = new DeathTask(player);
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Nullable
    public Player getPlayer() {
        return Bukkit.getPlayer(getUuid());
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role, boolean clansFollow){
        if(this.role != null)
            this.role.removePlayer(this);
        if(role == null)
            role = Role.NULL;
        this.role = role;
        if(clansFollow)
            this.role.getDefaultClans().addPlayer(this);
        getInfoData().setDataInfo(InfoData.InfoList.SEXE.name(), role.getSexe().name());
        role.addPlayer(this);
    }

    public Runnable getDeathTask(){
        return this.deathTask;
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

    public LinkData getLinkData(HPlayer hPlayer){

        if(linkData.containsKey(hPlayer))
            return linkData.get(hPlayer);

        linkData.put(hPlayer, new LinkData(this, hPlayer));
        return getLinkData(hPlayer);

    }

    public boolean hasMarriedReason(Reason reason){
        for(LinkData linkData : this.linkData.values())
            if(linkData.getMariedLinkReason() != null && linkData.getMariedLinkReason().isReason(reason))
                return true;
            return false;
    }

    public List<HPlayer> getMarriedPlayer(Reason reason){
        List<HPlayer> playerList = new ArrayList<>();
        for(LinkData linkData : this.linkData.values())
            if(linkData.getMariedLinkReason() != null && linkData.getMariedLinkReason().isReason(reason))
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public List<HPlayer> getMarriedPlayerList(){
        List<HPlayer> playerList = new ArrayList<>();
        for(LinkData linkData : this.linkData.values())
            if(linkData.getMariedLinkReason() != null)
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public boolean hisMarried(){
        return !getMarriedPlayerList().isEmpty();
    }

    public boolean hasDeathLinkReason(Reason reason){
        for(LinkData linkData : this.linkData.values())
            if(linkData.getDeathLinkReason() != null && linkData.getDeathLinkReason().isReason(reason))
                return true;
        return false;
    }

    public List<HPlayer> getDeathLinkPlayer(){
        List<HPlayer> playerList = new ArrayList<>();
        for(LinkData linkData : this.linkData.values())
            if(linkData.getDeathLinkReason() != null)
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public List<HPlayer> getDeathLinkPlayer(Reason reason){
        List<HPlayer> playerList = new ArrayList<>();
        for(LinkData linkData : this.linkData.values())
            if(linkData.getDeathLinkReason() != null && linkData.getDeathLinkReason().isReason(reason))
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public Clans getClans(){
        return Clans.getClans(this);
    }

    public boolean hasMalediction() {
        return !this.mrList.isEmpty();
    }

    public int getMaledictionPower(){
        return this.mrList.size();
    }

     public void addMaledictionReason(Reason mr){
        if(!hasMaledictionReason(mr)) {
            Player player = getPlayer();
            if(player == null)
                return;
            TitlePacket.send(player, 2, 5, 2, "Malédiction", "Raison: "+mr.getName());
            player.playSound(player.getLocation(), "mob.guardian.curse", 2.0f, 2.0f);
            player.sendMessage(ChatColor.GOLD + "Tu as reçu la malediction en raison de: " + mr.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 1));
            mrList.add(mr);
        }
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
        if(playerState == null)
            playerState = PlayerState.DISCONNECTED;
        this.playerState = playerState;
    }

    public void setKiller(Entity killer, Role role){
        this.killer = killer;
        if(role == null)
            role = Role.NULL;
        this.roleKiller = role;
    }

    @Nullable
    public Entity getKiller(){
        return killer;
    }

    @Nullable
    public Role getKillerRole(){
        return roleKiller;
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

    public InfoData getInfoData(){
        return infoData;
    }

}
