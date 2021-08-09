package fr.xilitra.higurashiuhc.game.clans.hinamizawa;

import fr.xilitra.higurashiuhc.game.clans.Clans;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hinamizawa extends Clans {

    private static Map<String, Clans> subClans = new HashMap<>();

    public Hinamizawa(String name) {
        super(name);
        subClans.put("Membre du club", new MemberOfClub("Membre du club"));
        subClans.put("Sonozaki", new Sonozaki("Sonozaki"));
    }

    public void addPlayerToSubClans(String clans, Player p){

        Clans newClans = subClans.get(clans);

        players.add(p);
        newClans.addPlayer(p);

        subClans.replace(clans, newClans);
    }

    public void removePlayerToSubClans(String clans, Player p) {
        Clans newClans = subClans.get(clans);

        players.remove(p);
        newClans.getPlayers().remove(p);

        subClans.replace(clans, newClans);
    }

    public static Map<String, Clans> getSubClans() {
        return subClans;
    }
}
