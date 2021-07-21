package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.task.GameTask;
import fr.xilitra.higurashiuhc.game.task.StartTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.scenario.Scenario;
import io.reactivex.rxjava3.core.Single;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class GameManager {

    private GameStates states;
    private Map<UUID, HPlayer> spectator = new HashMap<>();
    private Map<UUID, HPlayer > players = new HashMap<>();
    private Scenario scenario;
    private int episode = 0;

    public void config(){
        setStates(GameStates.CONFIG);
    }

    public void start() throws InstantiationException, IllegalAccessException {
        Runnable runnable = new StartTask();

        List<Role> roles = Arrays.asList(Role.values());


        for(HPlayer player : this.players.values()){
            int number = new Random().nextInt(roles.size());

            Role role = roles.get(number);

            player.setRole((RoleTemplate) role.getRole().newInstance());
            players.replace(player.getUuid(), player);

            roles.remove(role);

            Bukkit.getPluginManager().callEvent(new RoleSelected(player));
        }


        Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), runnable);
        this.setStates(GameStates.START);
    }

    public void game(){
        Runnable runnable = new GameTask();
        Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), runnable);
        this.setStates(GameStates.GAME);
    }


    public GameStates getStates() {
        return states;
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
}
