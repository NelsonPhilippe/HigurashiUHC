package fr.xilitra.higurashiuhc;

import org.bukkit.plugin.java.JavaPlugin;

public final class HigurashiUHC extends JavaPlugin {

    private static HigurashiUHC instance;

    @Override
    public void onEnable() {
        instance = this;

    }

    public static HigurashiUHC getInstance(){
        return instance;
    }

    @Override
    public void onDisable() {

    }
}
