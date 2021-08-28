package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Kumagai extends Role {

    private List<String> CompareClanUsed = new ArrayList<>();

    public Kumagai() {
        super("Kumagai", Gender.HOMME, Police.getClans(), 1);
    }

    public List<String> getCompareClanUsed() {
        return CompareClanUsed;
    }

    public void addClanToCompareUsed(String clan){
        CompareClanUsed.add(clan);
    }

    @Override
    public void onKill(EntityDamageEvent de, HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent de, HPlayer killed) {

    }
}
