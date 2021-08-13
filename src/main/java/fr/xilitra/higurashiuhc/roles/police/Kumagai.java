package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

import java.util.ArrayList;
import java.util.List;

public class Kumagai extends RoleTemplate {

    private List<String> CompareClanUsed = new ArrayList<>();

    public Kumagai() {
        super("Kumagai", Gender.HOMME);
    }

    public List<String> getCompareClanUsed() {
        return CompareClanUsed;
    }

    public void addClanToCompareUsed(String clan){
        CompareClanUsed.add(clan);
    }
}
