package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.task.taskClass.GameTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.RikaDeathTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.StartTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.*;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.*;

public class GameManager {

    private final Map<UUID, HPlayer> players = new HashMap<>();
    private final Runnable rikaDeathTask;
    private GameStates states;
    private int episode = 0;
    private double worldBorder = HigurashiUHC.getInstance().getConfig().getDouble("worldborder");
    private boolean watanagashi;

    public GameManager() {
        rikaDeathTask = new RikaDeathTask();
    }

    public void config() {
        setStates(GameStates.CONFIG);
    }

    public void start() {
        this.setStates(GameStates.START);

        ArrayList<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        roles.remove(Role.NULL);

        Sound sound = Sound.valueOf(HigurashiUHC.getInstance().getConfig().getString("game.startsound"));

        ScenarioList sl = ScenarioList.activateScenario();

        for (Player player : Bukkit.getOnlinePlayers())
            if (sl == null)
                player.sendMessage(ChatColor.RED + "Aucun scenario d'activé");
            else
                player.sendMessage(ChatColor.WHITE + "Le scenario: " + ChatColor.GREEN + sl.getScenario().getName() + ChatColor.WHITE + " est activé");

        for (HPlayer player : getHPlayerWithState(PlayerState.WAITING_ROLE)) {

            if (player.getPlayer() == null)
                continue;

            player.getPlayer().getInventory().clear();

            int number = new Random().nextInt(roles.size());

            Role role = roles.get(number);

            if (role.isRole(Role.JIRO_TOMITAKE)) {
                role = Role.MERCENAIRE;
                player.getInfoData().setDataInfo("hiddenJiro", true);
            }
            player.setRole(role, true);
            TitlePacket.send(player.getPlayer(), 2, 5, 2, role.getName(), "");
            player.getPlayer().sendMessage(role.getDecription());
            role.getDefaultClans().addPlayer(player);

            players.replace(player.getUuid(), player);
            player.setPlayerState(PlayerState.INGAME);


            if (role.isRole(Role.AKASAKA)) {
                player.getPlayer().getInventory().addItem(MatraqueItem.matraqueItem.getItemStack());
            }

            if (ScenarioList.DOLL.isActive() && role.isRole(Role.KEIICHI_MAEBARA)) {
                player.getPlayer().getInventory().addItem(DollItem.dollItem.getItemStack());
            }

            if (role.isRole(Role.MION_SONOZAKI, Role.SHION_SONOSAKI)) {
                player.getPlayer().setHealth(22);
            }

            if (role.isRole(Role.SATOSHI_HOJO)) {
                player.addMaledictionReason(Reason.SATOSHI_HOJO);
                player.getInfoData().setDataInfo(InfoData.InfoList.CLAN.name(), Clans.HINAMIZAWA.getName());
            }

            player.getPlayer().playSound(player.getPlayer().getLocation(), sound, 1, 1);
            Bukkit.getPluginManager().callEvent(new RoleSelected(player));

        }

        new StartTask().runTaskTimer(1000, 1000);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game() {
        new GameTask().runTaskTimer(1000, 1000);

        HPlayer hanyu = Role.HANYU.getHPlayer();
        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if (hanyu != null && hanyu.getPlayer() != null && rika != null && rika.getPlayer() != null) {
            hanyu.getPlayer().sendMessage("Rika est incarnée par: " + rika.getName());
            rika.getPlayer().sendMessage("Hanyu est incarnée par: " + hanyu.getName());
        }

        HPlayer satoshi = Role.SATOSHI_HOJO.getHPlayer();
        if (satoshi != null && satoshi.getPlayer() != null) {

            List<UUID> memberOfClub = Clans.MEMBER_OF_CLUB.getUUIDList();
            if (!memberOfClub.isEmpty()) {

                UUID luckyOrNot = memberOfClub.get(new Random().nextInt(memberOfClub.size()));
                satoshi.getPlayer().sendMessage("Le joueur: " + Objects.requireNonNull(HigurashiUHC.getGameManager().getHPlayer(luckyOrNot)).getName() + " est un membre du club");

            } else
                satoshi.getPlayer().sendMessage("Il n'y a aucun membre dans le clan du " + Clans.MEMBER_OF_CLUB.getName());

        }

        HPlayer okonogi = Role.OKONOGI.getHPlayer();
        HPlayer miyoTakano = Role.MIYO_TAKANO.getHPlayer();

        if (okonogi != null && okonogi.getPlayer() != null && miyoTakano != null)
            okonogi.getPlayer().sendMessage("Miyo Takano est joué par: " + miyoTakano.getName());

        HPlayer kasai = Role.KASAI.getHPlayer();
        HPlayer shion = Role.SHION_SONOSAKI.getHPlayer();

        if (kasai != null && kasai.getPlayer() != null && shion != null)
            kasai.getPlayer().sendMessage("Shion est joué par: " + shion.getName());

        List<HPlayer> hPlayerList = Role.MERCENAIRE.getHPlayerList();
        KitList[] listKit = KitList.values();

        for (HPlayer mercenaire : hPlayerList) {

            KitList kit = listKit[new Random().nextInt(listKit.length)];
            mercenaire.setKit(kit);

            String message = "Vous avez recu le kit: " + kit.name();
            if (kit == KitList.VOLEUR) {
                List<Commands> stolable = Commands.getStoleCommande();
                Commands stole = stolable.get(new Random().nextInt(stolable.size()));
                mercenaire.addCommandAccess(stole);
                message += ", vous avez recu la commande: " + stole.name();
            }

            if (mercenaire.getPlayer() != null)
                mercenaire.getPlayer().sendMessage(message);

        }

        this.setStates(GameStates.GAME);
    }

    public void startRikaDeathTask() {
        if (!((RikaDeathTask) rikaDeathTask).isRunning()) {
            ((RikaDeathTask) rikaDeathTask).runTaskTimer(1000, 1000);
        }
    }

    public boolean isWatanagashi() {
        return watanagashi;
    }

    public void setWatanagashi(boolean watanagashi) {
        this.watanagashi = watanagashi;
    }

    public GameStates getStates() {
        return states;
    }

    public void setStates(GameStates states) {
        this.states = states;
    }

    public Map<UUID, HPlayer> getHPlayerList() {
        return players;
    }

    public void addHPlayer(HPlayer player) {
        players.put(player.getUuid(), player);
    }

    public HPlayer removeHPlayer(HPlayer player) {
        return players.remove(player.getUuid());
    }

    public HPlayer removeHPlayer(UUID player) {
        return players.remove(player);
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int ep) {
        this.episode = ep;
    }

    @Nullable
    public HPlayer getHPlayer(UUID uuid) {
        return players.get(uuid);
    }

    @Nullable
    public HPlayer getHPlayer(String name) {
        for (HPlayer hPlayer : players.values())
            if (hPlayer.getName().equals(name))
                return hPlayer;
        return null;
    }

    public double getWorldBorder() {
        return worldBorder;
    }

    public void setWorldBorder(double worldBorder) {
        this.worldBorder = worldBorder;
    }

    public List<HPlayer> getHPlayerWithState(PlayerState... playerState) {

        List<HPlayer> playerList = new ArrayList<>();

        for (HPlayer player : this.players.values())
            if (player.getPlayerState().isState(playerState))
                playerList.add(player);

        return playerList;

    }

}
