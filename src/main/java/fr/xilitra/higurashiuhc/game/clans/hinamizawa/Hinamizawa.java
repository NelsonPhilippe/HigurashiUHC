package fr.xilitra.higurashiuhc.game.clans.hinamizawa;

import fr.xilitra.higurashiuhc.game.clans.Clans;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hinamizawa extends Clans {

    public static Hinamizawa hinamizawa = new Hinamizawa();

    public static Hinamizawa getClans(){
        return hinamizawa;
    }

    public Hinamizawa() {
        super("Hinamizawa");
    }

    @Override
    public boolean isSubClans() {
        return false;
    }

    @Override
    public Clans getMajorClans() {
        return null;
    }

}
