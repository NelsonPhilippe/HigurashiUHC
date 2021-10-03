package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.mercenaires.MercenaireAction;
import org.bukkit.entity.Player;

public class AssassinCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {
        Player p = hPlayer.getPlayer();
        if (p == null)
            return false;
        if (strings.length == 3) {
            HPlayer targetHPlayerMercenaire = HigurashiUHC.getGameManager().getHPlayer(strings[1]);
            HPlayer targetHPlayerVictim = HigurashiUHC.getGameManager().getHPlayer(strings[2]);

            if (targetHPlayerVictim == null) return true;

            if (targetHPlayerMercenaire == null) return true;

            if (targetHPlayerMercenaire.getRole().isRole(Role.MERCENAIRE)) {

                MercenaireAction roleTemplate = (MercenaireAction) targetHPlayerMercenaire.getRole().getRoleAction();
                roleTemplate.setCible(targetHPlayerVictim);

                return true;

            }

        }
        return false;
    }
}
