package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KumagaiAction implements RoleAction {

    @Override
    public Role getLinkedRole(){
        return Role.KUMAGAI;
    }

    private final List<String> CompareClanUsed = new ArrayList<>();

    public List<String> getCompareClanUsed() {
        return CompareClanUsed;
    }

    public void addClanToCompareUsed(String clan) {
        CompareClanUsed.add(clan);
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §eKumagai (garçon) : \n" +
                "\n" +
                "§eKumagai §6doit gagner avec §9Hinamizawa §6et fait partie du camp de la §epolice§6. \n" +
                "§eKumagai §6peut comparer un joueur avec un camp avec la commande §5'/h "+ Commands.COMPARER.getInitials()+
        " <joueur> <nom camp>' §6(§9Hinamizawa, §bClub, §3Sonozaki, §ePolice, §4Mercenaire, §2neutre§6) Il aura la confirmation s’il fait partie ou non du camp mentionné. \n" +
                "§6Ce pouvoir est utilisable une fois par camp.";
    }

}
