package fr.xilitra.higurashiuhc;

import fr.xilitra.higurashiuhc.game.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class HigurashiUHC extends JavaPlugin {

    private static HigurashiUHC instance;
    private static GameManager manager;

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

    @Override
    public void onDisable() {

    }
}
