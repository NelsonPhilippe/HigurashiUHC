package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.config.ConfigGestion;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.event.gamestate.GameStateChangeEvent;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.task.TaskRunner;
import fr.xilitra.higurashiuhc.game.task.taskClass.GameTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.StartTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.*;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;

public class GameManager {

    private final Map<UUID, HPlayer> players = new HashMap<>();
    private GameStates states;
    private int episode = 0;
    private WataEnum wataEnum = WataEnum.BEFORE;
    private final ConfigGestion configGestion;

    public GameManager(){
        File file = new File(HigurashiUHC.getInstance().getDataFolder().getAbsoluteFile()+File.separator+"config");
        if(!file.exists())
            file.mkdir();
        this.configGestion = new ConfigGestion(file);
    }

    public static int getWataEpisode() {
        return HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.EPISODE_WATANAGASHI);
    }

    public ConfigGestion getConfigGestion(){
        return configGestion;
    }

    public void config() {
        setStates(GameStates.CONFIG);
    }

    public void start() {
        this.setStates(GameStates.START);

        ArrayList<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
        roles.remove(Role.NULL);
        roles.remove(Role.TEPPEI_HOJO);

        Sound sound = Sound.valueOf(HigurashiUHC.getGameManager().getConfigGestion().getConfig().getString(ConfigLocation.SOUND_ONSTART));

        ScenarioList sl = ScenarioList.activateScenario();

        for (Player player : Bukkit.getOnlinePlayers())
            if (sl == null)
                player.sendMessage(ChatColor.RED + "Aucun scenario d'activé");
            else
                player.sendMessage(ChatColor.WHITE + "Le scenario: " + ChatColor.GREEN + sl.getScenario().getName() + ChatColor.WHITE + " est activé");

        for (HPlayer hPlayer : getHPlayerWithState(PlayerState.WAITING_ROLE)) {

            Player player = hPlayer.getPlayer();

            if (player == null)
                continue;

            player.getInventory().clear();

            int number = new Random().nextInt(roles.size());

            Role role = roles.get(number);
            roles.remove(role);

            if (role.isRole(Role.JIRO_TOMITAKE)) {
                role = Role.MERCENAIRE;
                hPlayer.getInfoData().setDataInfo("hiddenJiro", true);
            }

            hPlayer.setRole(role, true);
            TitlePacket.send(player, 2, 5, 2, role.getName(), "");
            player.sendMessage(role.getRoleAction().getDescription());

            players.replace(hPlayer.getUUID(), hPlayer);
            hPlayer.setPlayerState(PlayerState.INGAME);

            player.playSound(player.getLocation(), sound, 1, 1);

        }

        new StartTask().runTaskTimer(1000, 1000);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game() {

        this.setStates(GameStates.GAME);

        for (HPlayer hPlayer : HigurashiUHC.getGameManager().getHPlayerList().values())
            hPlayer.getRole().addPlayer(hPlayer);

        for (Role value : Role.values())
            value.getRoleAction().onGameStart();

        new GameTask().runTaskTimer(1000, 1000);

    }

    public void checkWin(){

        List<Clans> availableClans = new ArrayList<>();
        boolean withoutClans = false;
        List<HPlayer> remainPlayer = HigurashiUHC.getGameManager().getHPlayerWithState(PlayerState.INGAME);

        for(HPlayer hPlayer : remainPlayer){
            Clans clans = hPlayer.getClans();
            if(clans != null)
                availableClans.add(clans.getMajorClans());
            else withoutClans = true;
        }

        if(remainPlayer.size() <= 2){

            if(remainPlayer.size() == 2 && remainPlayer.get(0).getLinkData(remainPlayer.get(1)).isWinLinked()) {
                win(remainPlayer.get(0), remainPlayer.get(1));
                return;
            }else if(remainPlayer.size() == 1){
                if(remainPlayer.get(0).getClans() != null)
                    win(remainPlayer.get(0).getClans());
                else
                    win(remainPlayer.get(0));
                return;
            }else if(remainPlayer.size() == 0){
                win();
                return;
            }

        }

        if(availableClans.size() == 1 && withoutClans) {
            win(availableClans.get(0));
        }

    }

    public void win(Object... object){
        TaskRunner.stopAllTask();
    }

    public WataEnum getWataState() {
        return wataEnum;
    }

    public void setWataState(WataEnum wataEnum) {
        if (getWataState() == wataEnum)
            return;
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
        this.states = states;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(states));
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
        if (this.episode == ep)
            return;
        this.episode = ep;
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

        List<HPlayer> playerList = new ArrayList<>();

        for (HPlayer player : this.players.values())
            if (player.getPlayerState().isState(playerState))
                playerList.add(player);

        return playerList;

    }

    public WorldBorder getWorldBorder(){
        return Bukkit.getWorld("world").getWorldBorder();
    }

}
