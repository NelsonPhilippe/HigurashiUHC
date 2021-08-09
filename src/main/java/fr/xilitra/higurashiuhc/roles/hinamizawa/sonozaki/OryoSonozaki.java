package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class OryoSonozaki extends RoleTemplate {

    private Map<HPlayer, Integer> voteBan = new HashMap<>();
    private boolean asVoted;

    public OryoSonozaki() {
        super("Oryo Sonozaki", Gender.FEMME);

        asVoted = false;

        for(HPlayer player : HigurashiUHC.getGameManager().getPlayers().values()){
            voteBan.put(player, 0);
        }
    }

    public void addVote(HPlayer hPlayer){
        voteBan.put(hPlayer, voteBan.get(hPlayer) + 1);
    }


    public boolean isAsVoted() {
        return asVoted;
    }

    public void setAsVoted(boolean asVoted) {
        this.asVoted = asVoted;
    }

    public Map<HPlayer, Integer> getVoteBan() {
        return voteBan;
    }
}
