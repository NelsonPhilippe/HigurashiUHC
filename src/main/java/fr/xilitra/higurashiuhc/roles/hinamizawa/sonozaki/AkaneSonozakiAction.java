package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class AkaneSonozakiAction implements RoleAction {

    private int nextDaySwap;

    public AkaneSonozakiAction() {
        nextDaySwap = 0;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §3Akane Sonozaki (fille) : \n" +
                "\n" +
                "§6Akane §6doit gagner avec §9Hinamizawa §6tout en faisant partie du clan §3Sonozaki. \n" +
                "§6Avec la commande §5“/h inverser <joueur1> <joueur2>”, §3Akane §6pourra inverser la position de deux joueurs, deux fois dans la partie. \n" +
                "(pouvoir utilisable une fois par épisode)";
    }

    public int getNextDaySwap() {
        return nextDaySwap;
    }

    public void setNextDaySwap(int nextDaySwap) {
        this.nextDaySwap = nextDaySwap;
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
