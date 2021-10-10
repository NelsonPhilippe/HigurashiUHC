package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.task.taskClass.MiyoTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiyoTakanoAction extends RoleAction implements Listener {

    public MiyoTask miyoTask = new MiyoTask();

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

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent watanagashiChangeEvent) {
        if (watanagashiChangeEvent.getWataEnum() == WataEnum.DURING) {
            HPlayer hPlayer = getLinkedRole().getHPlayer();
            if (hPlayer != null && hPlayer.getPlayer() != null)
                miyoTask.runTaskTimer(0L, 1000L);
        } else if (watanagashiChangeEvent.getWataEnum() == WataEnum.AFTER)
            this.miyoTask.stopTask();
    }

}
