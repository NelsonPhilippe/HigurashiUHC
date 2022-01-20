package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class KasaiAction implements RoleAction {

    private boolean isGiveForce = false;

    @Override
    public Role getLinkedRole(){
        return Role.KASAI;
    }

    public boolean isGiveForce() {
        return isGiveForce;
    }

    public void setGiveForce(boolean giveForce) {
        isGiveForce = giveForce;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §3Kasai (garçon) : \n" +
                "\n" +
                "§3Kasai§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du clan §3Sonozaki§6." +
                "§6Si §9Shion§6 meurt, il connaîtra le rôle du joueur qui l’aura tué." +
                "§3Kasai§6 peut utiliser la commande §5'/h "+ Commands.FORCE.getInitials() +"' ce qui lui donne l’effet force pendant 5 minutes qu’une fois dans la partie.";
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
        HPlayer kasai = Role.KASAI.getHPlayer();
        HPlayer shion = Role.SHION_SONOSAKI.getHPlayer();

        if (kasai != null && kasai.getPlayer() != null && shion != null)
            kasai.getPlayer().sendMessage("Shion§6 est §7'"+shion.getName()+"'." + shion.getName());
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
