package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class AkaneSonozakiAction implements RoleAction {

    private int nextDaySwap = 0;

    @Override
    public Role getLinkedRole(){
        return Role.AKANE_SONOZAKI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §3Akane Sonozaki (fille) : \n" +
                "\n" +
                "§6Akane §6doit gagner avec §9Hinamizawa §6tout en faisant partie du clan §3Sonozaki. \n" +
                "§6Avec la commande §5'/h "+ Commands.INVERSER.getInitials() +" <joueur1> <joueur2>', §3Akane §6pourra inverser la position de deux joueurs, deux fois dans la partie. \n" +
                "(pouvoir utilisable une fois par épisode)";
    }

    public int getNextDaySwap() {
        return nextDaySwap;
    }

    public void setNextDaySwap(int nextDaySwap) {
        this.nextDaySwap = nextDaySwap;
    }

}
