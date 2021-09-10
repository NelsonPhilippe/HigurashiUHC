package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

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
    public String getDecription() {
        return "";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
