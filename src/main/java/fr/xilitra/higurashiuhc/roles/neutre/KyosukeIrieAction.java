package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class KyosukeIrieAction implements RoleAction {

    @Override
    public Role getLinkedRole(){
        return Role.KYOSUKE_IRIE;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §2Kyosuke Irie (garçon) : \n" +
                "\n" +
                "§2Irie§6 est un rôle qui peut choisir son camp parmi §9Hinamizawa §6et les §4mercenaires §6avec la commande §5'/h "+ Commands.CLANS.getInitials() +" Hinamizawa/mercenaire. \n" +
                "§6Il aura la possibilité de connaître les effets d’un joueur (2 fois dans la partie) avec la commande §5'/h "+Commands.EFFET_LISTENER.getInitials()+" <joueur>'. \n" +
                "§2Irie §6a ensuite le pouvoir de faire disparaître tous les buffs et debuffs du joueur espionné une fois dans la partie avec la commande §5'/h "+Commands.EFFET_CLEAR.getInitials()+" <joueur>'.";
    }

}
