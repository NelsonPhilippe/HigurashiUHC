package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class OryoSonozakiAction extends RoleAction {

    private int voteBan;
    private HPlayer votedPlayer = null;

    public void addVote(){
        voteBan+=1;
    }


    public boolean isAsVoted() {
        return votedPlayer != null;
    }

    public void setAsVoted(HPlayer hPlayer) {
        this.votedPlayer = hPlayer;
    }

    public int getTotalVote() {
        return voteBan;
    }

    public HPlayer getVotedPlayer(){
        return votedPlayer;
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
