package fr.xilitra.higurashiuhc;

import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class HigurashiUHC extends JavaPlugin {

    private static HigurashiUHC instance;
    private static GameManager manager;
    private static Map<UUID, Scoreboard> scoreboardMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        manager = this.manager;
    }

    public static HigurashiUHC getInstance(){
        return instance;
    }

    public static GameManager getGameManager(){
        return manager;
    }

    public static Map<UUID, Scoreboard> getScoreboardMap(){
        return scoreboardMap;
    }

    public static void addScoreboard(UUID uuid, Scoreboard scoreboard){
        scoreboardMap.put(uuid, scoreboard);
    }

    @Override
    public void onDisable() {

    }
}
