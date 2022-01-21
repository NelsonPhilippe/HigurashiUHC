package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

public class ClansCmd extends CommandsExecutor {

    public ClansCmd() {
        super("[Clans]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {
        if (Clans.getClans(hPlayer) == Clans.NEUTRE) {
            sendError(p, "Tu as déjà un clans");
            return false;
        }
        if (strings.length != 2) {
            sendError(p, "Merci d'indiquer le clans");
            return false;
        }
        Clans clans = Clans.getClans(strings[1]);
        if (clans == null) {
            sendError(p, "Clans non reconnue");
            return false;
        }

        if (!(clans.isClans(Clans.HINAMIZAWA) || clans.isClans(Clans.MERCENAIRE))) {
            sendError(p, "Erreur, tu ne peux choisir que entre: "+Clans.HINAMIZAWA.getName()+" ou "+Clans.MERCENAIRE.getName());
            return false;
        }

        clans.addPlayer(hPlayer);
        if(clans.isClans(Clans.HINAMIZAWA))
            sendError(p, "§a§oVous avez choisi le camp d’§9§oHinamizawa.");
        else
            sendError(p, "§a§oVous avez choisi le camp des §4§omercenaires.");
        return true;
    }
}
