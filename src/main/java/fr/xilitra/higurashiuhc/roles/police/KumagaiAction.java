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
