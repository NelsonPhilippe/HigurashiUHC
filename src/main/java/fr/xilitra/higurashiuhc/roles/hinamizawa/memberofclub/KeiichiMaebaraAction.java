package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class KeiichiMaebaraAction extends RoleAction implements Listener {

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if (ScenarioList.OYASHIRO.isActive())
            ScenarioList.OYASHIRO.getScenario().solution(2);

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {
        if (ScenarioList.DOLL.isActive()) {
            HPlayer hPlayer = getLinkedRole().getHPlayer();
            if (hPlayer == null)
                return;
            Player player = hPlayer.getPlayer();
            if (player == null)
                return;
            player.getInventory().addItem(DollItem.dollItem.getItemStack());
        }
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
