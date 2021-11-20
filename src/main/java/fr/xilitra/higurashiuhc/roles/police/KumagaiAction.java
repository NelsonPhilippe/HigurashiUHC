package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KumagaiAction extends RoleAction {

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
                "§eKumagai §6peut comparer un joueur avec un camp avec la commande §5“/h comparer <joueur> <nom camp>” §6(§9Hinamizawa, §bClub, §3Sonozaki, §ePolice, §4Mercenaire, §2neutre§6) Il aura la confirmation s’il fait partie ou non du camp mentionné. \n" +
                "§6Ce pouvoir est utilisable une fois par camp.";
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
