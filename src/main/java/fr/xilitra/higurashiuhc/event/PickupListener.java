package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PickupListener implements Listener {

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null)
            return;

        ItemStack item = e.getItem().getItemStack();

        if (!item.getItemMeta().hasLore()) return;

        String lore = item.getItemMeta().getLore().get(0);
        Role playerRole = hPlayer.getRole();

        if (lore.equalsIgnoreCase(CustomCraft.baseballBat.getLore())) {

            if (!playerRole.isRole(Role.KEIICHI_MAEBARA, Role.SATOSHI_HOJO)) {
                e.setCancelled(true);
            }

        } else if (ScenarioList.DOLL.isActive() && lore.equalsIgnoreCase(DollItem.dollItem.getLore())) {

            if (playerRole.isRole(Role.SHION_SONOSAKI)) {
                ScenarioList.DOLL.getScenario().solution(1, hPlayer);
            } else if (playerRole.isRole(Role.RENA_RYUGU)) {
                ScenarioList.DOLL.getScenario().solution(2);
            } else if (playerRole.isRole(Role.MION_SONOZAKI)) {
                ScenarioList.DOLL.getScenario().solution(3);
            } else {
                ScenarioList.DOLL.getScenario().solution(4);
            }

            item.setType(Material.AIR);

        }

    }

}
