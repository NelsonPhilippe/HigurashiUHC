package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent watanagashiChangeEvent) {

        if (watanagashiChangeEvent.getWataEnum() != WataEnum.DURING)
            return;

        KeiichiMaebaraAction role = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();
        HPlayer hPlayer = role.getLinkedRole().getHPlayer();
        if (hPlayer == null)
            return;

        if (hPlayer.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
            Role shion = Role.SHION_SONOSAKI;
            if (shion.getHPlayer() != null && shion.getHPlayer().getPlayer() != null)
                shion.getHPlayer().getPlayer().sendMessage("Je te donne une petite info: " + hPlayer.getName() + " est marié à " + hPlayer.getMarriedPlayer(Reason.DOLL_TRAGEDY).get(0).getName());
        }

        Player keiichiPlayer = hPlayer.getPlayer();
        if (keiichiPlayer == null)
            return;

        Inventory inventory = keiichiPlayer.getInventory();

        for (ItemStack itemStack : inventory.getContents()) {

            if (itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(DollItem.dollItem.getLore())) {

                ScenarioList.DOLL.getScenario().solution(4);
                itemStack.setType(Material.AIR);
                break;

            }

        }

    }

}
