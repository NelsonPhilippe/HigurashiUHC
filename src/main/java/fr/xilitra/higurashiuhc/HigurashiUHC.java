package fr.xilitra.higurashiuhc;

import fr.xilitra.higurashiuhc.command.*;
import fr.xilitra.higurashiuhc.command.DebugCmd;
import fr.xilitra.higurashiuhc.command.HigurashiCmd;
import fr.xilitra.higurashiuhc.command.RessuciteCmd;
import fr.xilitra.higurashiuhc.event.*;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import fr.xilitra.higurashiuhc.utils.packets.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        manager = new GameManager();

        //register default config
        saveDefaultConfig();

        //registry
        registerEvents();
        registerCommands();

        //register custom craft
        CustomCraft.addRecipeBaseball();

        manager.config();

    }

    private void registerEvents(){

        //general listener
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        this.getServer().getPluginManager().registerEvents(new PickupListener(), this);
        this.getServer().getPluginManager().registerEvents(new ConfigListener(), this);
        this.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new CraftEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new EpisodeListener(), this);

        //role listener
        this.getServer().getPluginManager().registerEvents(new RikaFurude(), this);
        this.getServer().getPluginManager().registerEvents(new Hanyu(), this);
        this.getServer().getPluginManager().registerEvents(new SatokoHojo(), this);
        this.getServer().getPluginManager().registerEvents(new RenaRyugu(), this);
        this.getServer().getPluginManager().registerEvents(new MionSonozaki(), this);
        this.getServer().getPluginManager().registerEvents(new ShionSonozaki(), this);
        this.getServer().getPluginManager().registerEvents(new KeiichiMaebara(), this);

    }

    private void registerCommands(){
        this.getCommand("ressucite").setExecutor(new RessuciteCmd());
        this.getCommand("h").setExecutor(new HigurashiCmd());
        this.getCommand("debug").setExecutor(new DebugCmd());
        this.getCommand("vote").setExecutor(new VoteCmd());
        this.getCommand("coupable").setExecutor(new CoupableCmd());
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
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("Redémarrage du serveur.");
        }
    }
}
