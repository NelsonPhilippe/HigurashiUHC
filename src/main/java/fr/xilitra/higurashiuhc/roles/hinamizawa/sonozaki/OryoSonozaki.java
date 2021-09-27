package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OryoSonozaki extends Role {

    private Map<HPlayer, Integer> voteBan = new HashMap<>();
    private boolean asVoted;

    public OryoSonozaki() {
        super("Oryo Sonozaki", Gender.FEMME, Clans.SONOZAKI, 1);

        asVoted = false;

        for(HPlayer player : HigurashiUHC.getGameManager().getHPlayerList().values()){
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

    @Override
    public String getDecription() {
        return "null";
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
