package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;

import java.util.ArrayList;
import java.util.List;

public class Kumagai extends Role {

    private List<String> CompareClanUsed = new ArrayList<>();

    public Kumagai() {
        super("Kumagai", Gender.HOMME, Police.getClans());
    }

    public List<String> getCompareClanUsed() {
        return CompareClanUsed;
    }

    public void addClanToCompareUsed(String clan){
        CompareClanUsed.add(clan);
    }
}
