package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsListener;
import fr.xilitra.higurashiuhc.config.ConfigGestion;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.event.*;
import fr.xilitra.higurashiuhc.event.gamestate.GameStateChangeEvent;
import fr.xilitra.higurashiuhc.event.gamestate.GameStateChangeListener;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.task.TaskRunner;
import fr.xilitra.higurashiuhc.game.task.taskClass.GameTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.StartTask;
import fr.xilitra.higurashiuhc.gui.config.MapMenu;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.*;
import java.util.logging.Level;

public class GameManager {

    private final Map<UUID, HPlayer> players = new HashMap<>();
    private GameStates states;
    private int episode = 0;
    private WataEnum wataEnum = WataEnum.BEFORE;
    private ConfigGestion configGestion;
    private GameTask gameTask = null;

    public void initConfig(){
        this.log("FINE) Initializing Config");
        File file = new File(HigurashiUHC.getInstance().getDataFolder().getAbsoluteFile()+File.separator+"config");
        if(!file.exists())
            if(file.mkdir())
                return;
        this.configGestion = new ConfigGestion(file);
        this.log("FINE) Config Initialized");
        setStates(GameStates.CONFIG);
    }

    public static int getWataEpisode() {
        return HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.EPISODE_WATANAGASHI);
    }

    public ConfigGestion getConfigGestion(){
        return configGestion;
    }

    public void log(String message){
        Bukkit.getLogger().log(Level.INFO, "Higurashi -> " + message);
    }

    public void start() {
        this.setStates(GameStates.START);

        ArrayList<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        if(!ScenarioList.MISTREATMENT.isActive()) {
            roles.remove(Role.TEPPEI_HOJO);
            roles.remove(Role.NOMURA);
        }
        roles.remove(Role.NULL);
        this.log("MCE) List active role: " + roles);

        Sound sound = Sound.valueOf(HigurashiUHC.getGameManager().getConfigGestion().getConfig().getString(ConfigLocation.SOUND_ONSTART));

        ScenarioList sl = ScenarioList.activateScenario();
        if(sl == null)
            this.log("INFO) No Scenario active");
        else this.log("INFO) Scenario Picked Up");

        for (Player player : Bukkit.getOnlinePlayers())
            if (sl == null)
                player.sendMessage(ChatColor.RED + "Aucun scenario d'activé");
            else
                player.sendMessage(ChatColor.WHITE + "Le scenario: " + ChatColor.GREEN + sl.getScenario().getName() + ChatColor.WHITE + " est activé");

            int i = 0;

        for (HPlayer hPlayer : getHPlayerWithState(PlayerState.WAITING_ROLE)) {

            i++;
            this.log("INFO) Start "+i+" to "+ hPlayer.getName());
            Player player = hPlayer.getPlayer();

            if (player == null) {
                this.log("MCE) "+i+" player not founded");
                continue;
            }

            player.getInventory().clear();

            int number = new Random().nextInt(roles.size());

            Role role = roles.get(number);
            roles.remove(role);


            hPlayer.setRole(role, true);
            player.playSound(player.getLocation(), sound, 1, 1);

        }

        new StartTask().runTaskTimer(1, 1);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game() {

        this.setStates(GameStates.GAME);

        registerGameCommands();
        registerGameEvents();

        for (Role value : Role.values())
            value.getRoleAction().onGameStart();

        gameTask = new GameTask();
        gameTask.runTaskTimer(1, 1);

    }

    private void registerGameEvents() {

        //general listener
        PluginManager pm = HigurashiUHC.getInstance().getServer().getPluginManager();
        Plugin plugin = HigurashiUHC.getInstance();
        pm.registerEvents(new InteractListener(), plugin);
        pm.registerEvents(new BlockBreakListener(), plugin);
        pm.registerEvents(new BlockPlaceListener(), plugin);
        pm.registerEvents(new PickupListener(), plugin);
        pm.registerEvents(new DamageListener(), plugin);
        pm.registerEvents(new CraftEvent(), plugin);
        pm.registerEvents(new MoveEvent(), plugin);
        pm.registerEvents(new EpisodeListener(), plugin);
        pm.registerEvents(new MapMenu(), plugin);
        pm.registerEvents(new GameStateChangeListener(), plugin);
        for(Role role : Role.values()) {
            RoleAction roleAction = role.getRoleAction();
            if (roleAction instanceof Listener) {
                HigurashiUHC.getGameManager().log("INFO) Registered Role Listener: " + roleAction.getClass().getName());
                pm.registerEvents((Listener) roleAction, plugin);
            }
        }

    }

    private void registerGameCommands() {
        new CommandsListener();
    }

    public void checkWin(){

        List<Clans> availableClans = new ArrayList<>();
        List<HPlayer> remainPlayer = HigurashiUHC.getGameManager().getHPlayerWithState(PlayerState.INGAME);

        if(remainPlayer.size() <= 2){

            if(remainPlayer.size() == 2 && remainPlayer.get(0).getLinkData(remainPlayer.get(1)).isWinLinked()) {
                win(remainPlayer.get(0), remainPlayer.get(1));
                return;
            }else if(remainPlayer.size() == 1){
                if(remainPlayer.get(0).getClans() != Clans.NEUTRE)
                    win(remainPlayer.get(0).getClans().getMajorClans());
                else
                    win(remainPlayer.get(0));
                return;
            }else if(remainPlayer.size() == 0){
                win();
                return;
            }

        }

        for(HPlayer hPlayer : remainPlayer){
            Clans clans = hPlayer.getClans();
            if(!availableClans.contains(clans))
                availableClans.add(clans.getMajorClans());
        }

        if(availableClans.size() == 1)
            if(availableClans.get(0) != Clans.NEUTRE)
            win(availableClans.get(0));

    }

    public void win(Object... object){
        log("Win launched");
        HigurashiUHC.getGameManager().setStates(GameStates.FINISH);
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("Wins launched: "+ Arrays.toString(object));
        });
        TaskRunner.stopAllTask();
    }

    public WataEnum getWataState() {
        return wataEnum;
    }

    public void setWataState(WataEnum wataEnum) {
        if (getWataState() == wataEnum)
            return;
        log("Watastate changed: "+getWataState()+ " -> "+wataEnum);
        if(wataEnum == WataEnum.AFTER)
            Bukkit.broadcastMessage("§c<La nuit du festival de la §5§nWatanagashi vient de se finir, on espère que vous avez passé une excellente nuit.");
        else if(wataEnum == WataEnum.DURING)
            Bukkit.broadcastMessage("§c<La nuit du festival de la §5§nWatanagashi vient de commencer, on vous souhaite à tous une agréable nuit.");
        Bukkit.getPluginManager().callEvent(new WatanagashiChangeEvent(getWataState()));
        this.wataEnum = wataEnum;
    }

    public boolean isWataState(WataEnum wataEnum) {
        return this.wataEnum == wataEnum;
    }

    public GameStates getStates() {
        return states;
    }

    public void setStates(GameStates states) {
        this.log("Changing State " + this.states + " -> " + states);
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(states));
        this.states = states;
    }

    public Map<UUID, HPlayer> getHPlayerList() {
        return players;
    }

    public void addHPlayer(HPlayer player) {
        players.put(player.getUUID(), player);
    }

    public HPlayer removePlayer(UUID player) {
        return players.remove(player);
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int ep) {

        this.log("Try change episode " + this.episode + " -> " + ep);
        if (this.episode == ep) {
            this.log(SystemColor.RED + "Episode not changed to " + this.episode + " Reason: episode equivalent to actual one");
            return;
        }

        this.log("Episode Changed to " + this.episode);
        broadcast(ChatColor.WHITE + "L'episode viens de changer: " + this.episode + " -> " + ep);
        this.episode = ep;
        this.gameTask.time = this.gameTask.timeEpisode;

        Bukkit.getServer().getPluginManager().callEvent(new EpisodeUpdate(HigurashiUHC.getGameManager().getEpisode()));

        setWataState(ep == GameManager.getWataEpisode() ? WataEnum.DURING : (ep > GameManager.getWataEpisode() ? WataEnum.AFTER : WataEnum.BEFORE));

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

    public List<HPlayer> getHPlayerWithState(PlayerState... playerState) {

        this.log("Searching for player with state: "+ Arrays.toString(playerState));
        List<HPlayer> playerList = new ArrayList<>();

        int i = 0;

        for (HPlayer player : this.players.values()) {
            i++;
            log("Player "+i+" -> "+player.getPlayerState());
            if (player.getPlayerState().isState(playerState)) {

                playerList.add(player);
            }
        }

        return playerList;

    }

    public WorldBorder getWorldBorder() {
        return Bukkit.getWorld("world").getWorldBorder();
    }

    public void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(message);
    }

    public void broadcast(PlayerState playerState, String message) {
        for (HPlayer hPlayer : HigurashiUHC.getGameManager().getHPlayerWithState(playerState)) {
            Player player = hPlayer.getPlayer();
            if (player == null)
                continue;
            player.sendMessage(message);
        }
    }

}
