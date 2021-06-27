package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.GameTask;
import fr.xilitra.higurashiuhc.game.task.StartTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private GameStates states;
    private Map<UUID, HPlayer > players = new HashMap<>();

    public void config(){
        setStates(GameStates.CONFIG);
    }

    public void start(){
        Runnable runnable = new StartTask();
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
}
