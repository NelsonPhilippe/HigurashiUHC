package fr.xilitra.higurashiuhc.player;

import com.avaje.ebean.validation.NotNull;
import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.taskClass.DeathTaskExecutor;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.util.*;

public class HPlayer {

    private final String name;
    private final UUID uuid;
    private final DeathTaskExecutor deathTask;
    private final Map<HPlayer, LinkData> linkData = new HashMap<>();
    private final List<Reason> mrList = new ArrayList<>();
    private final InfoData infoData;
    private Role role = Role.NULL;
    private Role roleKiller = null;
    private Entity killer = null;
    private boolean playerDontMove = false;
    private boolean chatOkonogi = false;
    private PlayerState playerState = PlayerState.WAITING_ROLE;
    private KitList kitList = null;
    private HashMap<Commands, Integer> commandsIntegerHashMap;
    private Player player;

    public HPlayer(String name, Player player) {
        this.name = name;
        this.uuid = player.getUniqueId();
        this.player = player;
        this.infoData = new InfoData(this);
        this.deathTask = new DeathTaskExecutor(player);
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Nullable
    public Player getPlayer() {
        if (player == null) {
            player = Bukkit.getPlayer(this.getUUID());
        }
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public @NotNull Role getRole() {
        return role;
    }

    public void setRole(Role role, boolean clansFollow) {
        if(role == null)
            return;
        if (this.role != null)
            this.role.removePlayer(this);
        if (role.isRole(Role.JIRO_TOMITAKE) && HigurashiUHC.getGameManager().getStates() != GameStates.GAME) {
            role = Role.MERCENAIRE;
            this.getInfoData().setDataInfo("hiddenJiro", true);
        }
        if(this.role == null)
            HigurashiUHC.getGameManager().log(getName() + ") Role changed null -> " + role.getName());
        else HigurashiUHC.getGameManager().log(getName() + ") Role changed " + this.role.getName() +" -> " + role.getName());
        this.role = role;
        if (clansFollow)
            this.role.getDefaultClans().addPlayer(this);
        this.commandsIntegerHashMap = this.role.getDefaultCommands();
        getInfoData().setDataInfo(InfoData.InfoList.SEXE.name(), role.getSexe().name());
        role.addPlayer(this);
        this.setPlayerState(PlayerState.INGAME);

        TitlePacket.send(player, 2, 5, 2, role.getName(), "");
        player.sendMessage(role.getRoleAction().getDescription());

    }

    public Runnable getDeathTask() {
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

    public LinkData getLinkData(HPlayer hPlayer) {

        if (linkData.containsKey(hPlayer))
            return linkData.get(hPlayer);

        linkData.put(hPlayer, new LinkData(this, hPlayer));
        return getLinkData(hPlayer);

    }

    public boolean hasMarriedReason(Reason reason) {
        for (LinkData linkData : this.linkData.values())
            if (linkData.getMariedLinkReason() != null && linkData.getMariedLinkReason().isReason(reason))
                return true;
        return false;
    }

    public List<HPlayer> getMarriedPlayer(Reason reason) {
        List<HPlayer> playerList = new ArrayList<>();
        for (LinkData linkData : this.linkData.values())
            if (linkData.getMariedLinkReason() != null && linkData.getMariedLinkReason().isReason(reason))
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public List<HPlayer> getMarriedPlayerList() {
        List<HPlayer> playerList = new ArrayList<>();
        for (LinkData linkData : this.linkData.values())
            if (linkData.getMariedLinkReason() != null)
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public boolean hisMarried() {
        return !getMarriedPlayerList().isEmpty();
    }

    public boolean hasDeathLinkReason(Reason reason) {
        for (LinkData linkData : this.linkData.values())
            if (linkData.getDeathLinkReason() != null && linkData.getDeathLinkReason().isReason(reason))
                return true;
        return false;
    }

    public List<HPlayer> getDeathLinkPlayer() {
        List<HPlayer> playerList = new ArrayList<>();
        for (LinkData linkData : this.linkData.values())
            if (linkData.getDeathLinkReason() != null)
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public List<HPlayer> getDeathLinkPlayer(Reason reason) {
        List<HPlayer> playerList = new ArrayList<>();
        for (LinkData linkData : this.linkData.values())
            if (linkData.getDeathLinkReason() != null && linkData.getDeathLinkReason().isReason(reason))
                playerList.add(linkData.getLinkedPlayer());
        return playerList;
    }

    public Clans getClans() {
        return Clans.getClans(this);
    }

    public boolean hasMalediction() {
        return !this.mrList.isEmpty();
    }

    public int getMaledictionPower() {
        return this.mrList.size();
    }

    public void addMaledictionReason(Reason mr) {
        if (!hasMaledictionReason(mr)) {
            HigurashiUHC.getGameManager().log(getName() + ") Malediction added -> " + mr.getName());
            mrList.add(mr);
            getRole().getRoleAction().onMaledictionReceived(this, mr);
            Clans.NEUTRE.addPlayer(this);
            Player player = getPlayer();
            if (player == null)
                return;
            TitlePacket.send(player, 2, 5, 2, "Malédiction", "Raison: " + mr.getName());
            player.playSound(player.getLocation(), "mob.guardian.curse", 2.0f, 2.0f);
            player.sendMessage("§cVous êtes atteint de la §8§omalédiction d’Oyashiro §c! \n" +
                    "\n" +
                    "§7La paranoïa vous gagne et vous rend plus fort. \n" +
                    "§7Votre objectif est maintenant de gagner seul et d’être le dernier en vie. \n" +
                    "§7Pour vous aider, votre force a été augmentée.\n" +
                    "§7Si vous arrivez à tuer tous les membres du §bclub, vous deviendrez plus résistant. \n" +
                    "§7À chaque membre du §bclub qui n’est pas atteint de la malédiction tué par vous, vous aurez un cœur supplémentaire. \n" +
                    "§7Votre destin est maintenant scellé. ");
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
        }
    }

    public void sendMessage(String message) {
        if (getPlayer() == null)
            return;
        getPlayer().sendMessage(message);
    }

    public void removeMaledictionReason(Reason mr) {
        mrList.remove(mr);
        HigurashiUHC.getGameManager().log(getName() + ") Malediction removed -> " + mr.getName());
        if(mrList.isEmpty()) {
            getRole().getDefaultClans().addPlayer(this);
        }
    }

    public boolean hasMaledictionReason(Reason... mrList) {
        for (Reason mr : mrList)
            if (this.mrList.contains(mr))
                return true;
        return false;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        if (playerState == null)
            playerState = PlayerState.DISCONNECTED;
        this.playerState = playerState;
    }

    public void setKiller(Entity killer, Role role) {
        this.killer = killer;
        this.roleKiller = role;
    }

    @Nullable
    public Entity getKiller() {
        return killer;
    }

    @Nullable
    public Role getKillerRole() {
        return roleKiller;
    }

    public boolean hasKit() {
        return kitList != null;
    }

    public KitList getKit() {
        return kitList;
    }

    public void setKit(KitList kitList) {
        this.kitList = kitList;
    }

    public InfoData getInfoData() {
        return infoData;
    }

    public void removeCommandAccess(Commands commands) {
        commandsIntegerHashMap.remove(commands);
    }

    public void useCommand(Commands commands) {
        int num = getCommandAccess(commands);
        if (num > 0)
            commandsIntegerHashMap.replace(commands, num - 1);
    }

    public boolean hasCommandAccess(Commands commands) {
        return getCommandAccess(commands) != 0;
    }

    public int getCommandAccess(Commands commands) {
        Integer value = commandsIntegerHashMap.get(commands);
        if (value == null)
            return 0;
        return value;
    }

    public void setCommandAccess(Commands commands, Integer number) {

        removeCommandAccess(commands);
        commandsIntegerHashMap.put(commands, number);

    }

    public void addCommandAccess(Commands commands) {

        setCommandAccess(commands, getCommandAccess(commands) + 1);

    }

}
