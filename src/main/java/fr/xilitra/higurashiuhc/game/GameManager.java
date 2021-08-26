package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.game.clans.Neutre;
import fr.xilitra.higurashiuhc.game.clans.Police;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import fr.xilitra.higurashiuhc.game.task.GameTask;
import fr.xilitra.higurashiuhc.game.task.RikaDeathTask;
import fr.xilitra.higurashiuhc.game.task.StartTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.scenario.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.*;

public class GameManager {

    private GameStates states;
    private Map<UUID, HPlayer> spectator = new HashMap<>();
    private Map<UUID, HPlayer > players = new HashMap<>();
    private Scenario scenario;
    private int episode = 0;
    private double worldBorder = HigurashiUHC.getInstance().getConfig().getDouble("worldborder");
    private Runnable rikaDeathTask = new RikaDeathTask();

    public GameManager(){
        new ClansManager();
    }

    public void config(){
        setStates(GameStates.CONFIG);

    }

    public void start() {
        this.setStates(GameStates.START);

        ArrayList<RoleList> roles = new ArrayList<>(Arrays.asList(RoleList.values()));


        for(HPlayer player : this.players.values()){

            player.getPlayer().getInventory().clear();

            int number = new Random().nextInt(roles.size());

            RoleList role = roles.get(number);

            roles.remove(number);

            player.setRoleList(role);
            players.replace(player.getUuid(), player);
            player.getInfo().put(KuraudoOishi.infoList.SEXE, role.getRole().getSexe().name());


            if(role.getRole().equals(RoleList.AKASAKA.getRole())) {
                player.getPlayer().getInventory().addItem(MatraqueItem.matraqueItem.getItemStack());
            }

            if(role.getRole().equals(RoleList.MION_SONOZAKI.getRole()) || role.getRole().equals(RoleList.SHION_SONOSAKI.getRole())){
                player.getPlayer().setHealth(22);
            }

            Bukkit.getPluginManager().callEvent(new RoleSelected(player));

        }

        TimerTask task = new StartTask();
        Timer run = new Timer("Start");
        run.scheduleAtFixedRate(task, 1000, 1000);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game(){
        TimerTask timerTask = new GameTask();
        Timer timer = new Timer("Game");
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        this.setStates(GameStates.GAME);
    }

    public void startRikaDeathTask(){
        if(!((RikaDeathTask) rikaDeathTask).isStarted()){
            Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), rikaDeathTask);
        }
    }


    public GameStates getStates() {
        return states;
    }

    public Map<UUID, HPlayer> getSpectator(){
        return spectator;
    }

    public Scenario getSelectedScenario(){
        return scenario;
    }

    public boolean scenarioIsSelected(){
        return scenario != null;
    }

    public void setStates(GameStates states) {
        this.states = states;
    }

    public Map<UUID, HPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(HPlayer player){
        players.put(player.getUuid(), player);
    }

    public void removePlayer(HPlayer player){
        players.remove(player.getUuid());
    }

    public void removePlayer(UUID player){
        players.remove(player);
    }

    public void setScenario(Scenario scenario){
        this.scenario = scenario;
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

    public HPlayer getPlayerWithRole(RoleList role){
        try{
            for(HPlayer player : players.values()){

                if(player.getRoleList() != null) {


                    if (role.getRole().equals(player.getRoleList().getRole())) {
                        return player;
                    }
                }
            }
        }catch (NullPointerException e){
            return null;
        }

        return null;
    }
}
