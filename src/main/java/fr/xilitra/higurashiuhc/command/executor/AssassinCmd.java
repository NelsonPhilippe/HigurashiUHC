package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.mercenaires.MercenaireAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AssassinCmd extends CommandsExecutor {

    public AssassinCmd() {
        super("[Assassin]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 3) {
            HPlayer targetHPlayerMercenaire = HigurashiUHC.getGameManager().getHPlayer(strings[1]);
            HPlayer targetHPlayerVictim = HigurashiUHC.getGameManager().getHPlayer(strings[2]);

            if (targetHPlayerVictim == null){
                sendError(p, "Joueur: "+strings[2]+" inconnue");
                return false;
            }

            if (targetHPlayerMercenaire == null){
                sendError(p, "Joueur: "+strings[1]+" inconnue");
                return false;
            }

            if (targetHPlayerMercenaire.getRole().isRole(Role.MERCENAIRE)) {

                MercenaireAction roleTemplate = (MercenaireAction) targetHPlayerMercenaire.getRole().getRoleAction();
                roleTemplate.setCible(targetHPlayerVictim);

                sendOkay(p, "Tu as bien donné la mission");

                return true;

            }else{
                sendError(p, ChatColor.RED+"Le joueur: "+strings[1]+" n'est pas un mercenaire");
                return false;
            }

        }
        sendError(p, ChatColor.RED+"Merci de faire /h "+ Commands.ASSASSINER.getInitials()+" <Joueur mercenaire> <Joueur cible>");
        return false;
    }
}
