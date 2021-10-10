package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.KiichiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KiichiroKimiyoshiAction extends RoleAction {

    KiichiroTask kt = null;

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
        if (getLinkedRole().getHPlayer() == null)
            return;

        List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getHPlayerList().values());

        kt = new KiichiroTask(hPlayerList.get(new Random().nextInt(hPlayerList.size())));
        kt.runTaskTimer(0L, 1000L);
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
