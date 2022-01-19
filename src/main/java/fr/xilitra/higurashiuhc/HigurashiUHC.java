package fr.xilitra.higurashiuhc;

import fr.xilitra.higurashiuhc.command.CommandsConfig;
import fr.xilitra.higurashiuhc.command.CommandsListener;
import fr.xilitra.higurashiuhc.command.CommandsStart;
import fr.xilitra.higurashiuhc.command.DebugCmd;
import fr.xilitra.higurashiuhc.event.*;
import fr.xilitra.higurashiuhc.event.gamestate.GameStateChangeListener;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.gui.config.MapMenu;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import me.lulu.datounms.model.biome.BiomeData;
import me.lulu.datounms.v1_8_R3.BiomeReplacer;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class HigurashiUHC extends JavaPlugin {

    private static HigurashiUHC instance;
    private static GameManager manager;

    public static HigurashiUHC getInstance() {
        return instance;
    }

    public static GameManager getGameManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        instance = this;
        manager = new GameManager();
        manager.initConfig();

        HigurashiUHC.getGameManager().log("Debug) executed event: "+manager.getStates().name());


        //register default config
        saveDefaultConfig();

        //registry
        registerEvents();
        registerCommands();

        //register custom craft
        CustomCraft.addRecipeBaseball();

        BiomeReplacer biomeReplacer = new BiomeReplacer();
        biomeReplacer.swap(BiomeData.JUNGLE, BiomeData.FOREST);
        biomeReplacer.swap(BiomeData.OCEAN, BiomeData.FOREST);

    }

    private void registerEvents() {

        //general listener
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        this.getServer().getPluginManager().registerEvents(new PickupListener(), this);
        this.getServer().getPluginManager().registerEvents(new ConfigListener(), this);
        this.getServer().getPluginManager().registerEvents(new DamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new CraftEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new EpisodeListener(), this);
        this.getServer().getPluginManager().registerEvents(new MapMenu(), this);
        this.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        for(Role role : Role.values()) {
            RoleAction roleAction = role.getRoleAction();
            if (roleAction instanceof Listener) {
                HigurashiUHC.getGameManager().log("INFO) Registered Role Listener: " + roleAction.getClass().getName());
                Bukkit.getPluginManager().registerEvents((Listener) roleAction, this);
            }
        }

    }

    private void registerCommands() {
        new CommandsListener();
        new CommandsConfig();
        new CommandsStart();
        this.getCommand("debug").setExecutor(new DebugCmd());
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.getInventory().clear();
            p.kickPlayer("Redémarrage du serveur.");
        }

        WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
        worldBorder.reset();
    }
}
