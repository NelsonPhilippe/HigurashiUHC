package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import fr.xilitra.higurashiuhc.player.HPlayer;

import java.util.HashMap;
import java.util.Map;

public class OryoSonozaki extends Role {

    private Map<HPlayer, Integer> voteBan = new HashMap<>();
    private boolean asVoted;

    public OryoSonozaki() {
        super("Oryo Sonozaki", Gender.FEMME, Sonozaki.getClans());

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
