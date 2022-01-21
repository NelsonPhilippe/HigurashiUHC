package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.AkasakaAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RikaCmd extends CommandsExecutor {

    public RikaCmd() {
        super("[Rika]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 2) {

            Player target = Bukkit.getPlayer(strings[1]);

            if (target == null) {
                sendError(p, "Cible non trouvée");
                return false;
            }

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());

            if (targetHPlayer == null) {
                sendError(p, "Cible Introuvable");
                return false;
            }

            AkasakaAction akasakaAction = (AkasakaAction) targetHPlayer.getRole().getRoleAction();

            akasakaAction.setCountCompare(akasakaAction.getCountCompare() + 1);

            if (targetHPlayer.getRole().isRole(Role.RIKA_FURUDE)) {

                if (akasakaAction.getCountCompare() == 1) {
                    p.setMaxHealth(1.5);
                }

                if (akasakaAction.getCountCompare() == 2) {
                    p.setMaxHealth(1);
                }

                if (akasakaAction.getCountCompare() == 3) {
                    p.setMaxHealth(0.5);
                }

                sendOkay(p, strings[1] + " est Rika.");
                return true;
            }


            sendError(p, strings[1] + " n'est pas Rika.");
            return true;

        }
        return false;
    }
}
