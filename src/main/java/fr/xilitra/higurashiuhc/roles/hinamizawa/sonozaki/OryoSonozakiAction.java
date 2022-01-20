package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class OryoSonozakiAction implements RoleAction {

    private int voteBan;
    private HPlayer votedPlayer = null;

    @Override
    public Role getLinkedRole(){
        return Role.ORYO_SONOZAKI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §3Oryo Sonozaki (fille) : \n" +
                "\n" +
                "§3Oryo§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du clan §3Sonozaki§6." +
                "§6Elle peut décider de bannir un joueur avec la commande §5'/h "+ Commands.BAN.getInitials() +" <pseudo>'§6." +
                "§6Un vote se déclenchera pour les membres d’§9Hinamizawa§6." +
                "§6Si le nombre de votes est en faveur du bannissement, le joueur banni perdra donc 5 cœurs pendant 10 minutes. ";
    }

    public void addVote() {
        voteBan += 1;
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

    public HPlayer getVotedPlayer() {
        return votedPlayer;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
