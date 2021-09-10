package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.task.taskClass.GameTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.RikaDeathTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.StartTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.packets.TitlePacket;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.*;

public class GameManager {

    private GameStates states;
    private final Map<UUID, HPlayer> players = new HashMap<>();
    private ScenarioList scenarioList;
    private int episode = 0;
    private double worldBorder = HigurashiUHC.getInstance().getConfig().getDouble("worldborder");
    private final Runnable rikaDeathTask;
    private boolean watanagashi;

    public GameManager(){
        rikaDeathTask = new RikaDeathTask();
    }

    public void config(){
        setStates(GameStates.CONFIG);
    }

    public void start() {
        this.setStates(GameStates.START);

        ArrayList<RoleList> roles = new ArrayList<>(Arrays.asList(RoleList.values()));
        roles.remove(RoleList.NULL);

        Sound sound = Sound.valueOf(HigurashiUHC.getInstance().getConfig().getString("game.startsound"));

        for(HPlayer player : getPlayerWithState(PlayerState.WAITING_ROLE)){

            if(player.getPlayer() == null)
                continue;

            player.getPlayer().getInventory().clear();

            int number = new Random().nextInt(roles.size());

            RoleList role = roles.get(number);

            player.setRole(role.getRole());
            TitlePacket.send(player.getPlayer(), 2, 5, 2, role.getRole().getName(), "");
            player.getPlayer().sendMessage(role.getRole().getDecription());
            player.setClans(role.getRole().getDefaultClans());

            players.replace(player.getUuid(), player);
            player.getInfo().put(KuraudoOishi.infoList.SEXE, role.getRole().getSexe().name());
            player.setPlayerState(PlayerState.INGAME);


            if(role.getRole().isRole(RoleList.AKASAKA.getRole())) {
                player.getPlayer().getInventory().addItem(MatraqueItem.matraqueItem.getItemStack());
            }

            if(ScenarioList.DOLL.isActive() && role.getRole().isRole(RoleList.KEIICHI_MAEBARA.getRole())){
                player.getPlayer().getInventory().addItem(DollItem.dollItem.getItemStack());
            }

            if(role.getRole().isRole(RoleList.MION_SONOZAKI.getRole(), RoleList.SHION_SONOSAKI.getRole())){
                player.getPlayer().setHealth(22);
            }

            player.getPlayer().playSound(player.getPlayer().getLocation(), sound, 1, 1);
            Bukkit.getPluginManager().callEvent(new RoleSelected(player));

        }

        new StartTask().runTask(1000,1000);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game(){
        new GameTask().runTask(1000,1000);
        this.setStates(GameStates.GAME);
    }

    public void startRikaDeathTask(){
        if(!((RikaDeathTask) rikaDeathTask).isRunning()){
            ((RikaDeathTask) rikaDeathTask).runTask(1000,1000);
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

    public ScenarioList getSelectedScenario(){
        return scenarioList;
    }

    public boolean scenarioIsSelected(){
        return scenarioList != null;
    }

    public void setStates(GameStates states) {
        this.states = states;
    }

    public Map<UUID, HPlayer> getPlayerList() {
        return players;
    }

    public void addPlayer(HPlayer player){
        players.put(player.getUuid(), player);
    }

    public HPlayer removePlayer(HPlayer player){
        return players.remove(player.getUuid());
    }

    public HPlayer removePlayer(UUID player){
        return players.remove(player);
    }

    public void setScenario(ScenarioList scenarioList){
        this.scenarioList = scenarioList;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int ep){
        this.episode = ep;
    }

    public HPlayer getPlayer(UUID uuid){
        return players.get(uuid);
    }

    public double getWorldBorder() {
        return worldBorder;
    }

    public void setWorldBorder(double worldBorder) {
        this.worldBorder = worldBorder;
    }

    public List<HPlayer> getPlayerWithState(PlayerState... playerState){

        List<HPlayer> playerList = new ArrayList<>();

        for(HPlayer player : this.players.values())
            if (player.getPlayerState().isState(playerState))
                playerList.add(player);

        return playerList;

    }

}
