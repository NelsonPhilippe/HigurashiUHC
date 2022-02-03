package fr.xilitra.higurashiuhc;

import fr.xilitra.higurashiuhc.command.CommandsConfig;
import fr.xilitra.higurashiuhc.command.CommandsStart;
import fr.xilitra.higurashiuhc.command.DebugCmd;
import fr.xilitra.higurashiuhc.command.TaskDebugCmd;
import fr.xilitra.higurashiuhc.event.JoinListener;
import fr.xilitra.higurashiuhc.event.PlayerLeaveEvent;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import me.lulu.datounms.model.biome.BiomeData;
import me.lulu.datounms.v1_8_R3.BiomeReplacer;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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

    private void registerCommands() {
        new CommandsConfig();
        new CommandsStart();
        HigurashiUHC.getInstance().getCommand("debug").setExecutor(new DebugCmd());
        HigurashiUHC.getInstance().getCommand("taskdebug").setExecutor(new TaskDebugCmd());
    }

    private void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
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
