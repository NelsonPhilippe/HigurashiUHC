package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.MariedReason;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
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
    public void onPickupItem(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        ItemStack item = e.getItem().getItemStack();

        if(!item.getItemMeta().hasLore()) return;

        String lore = item.getItemMeta().getLore().get(0);
        String playerRole = hPlayer.getRoleList().getRole().getName();

        if(lore.equalsIgnoreCase(CustomCraft.baseballBat.getLore())){

            if(!playerRole.equals(RoleList.KEIICHI_MAEBARA.getRole().getName()) || !playerRole.equals(RoleList.SATOSHI_HOJO.getRole().getName())){
                e.setCancelled(true);
            }

        }else if(ScenarioList.DOLL.isActive() && lore.equalsIgnoreCase(DollItem.dollItem.getLore())){

            if(playerRole.equals(RoleList.SHION_SONOSAKI.getRole().getName())){
                ScenarioList.DOLL.getScenario().solution(1, hPlayer);
            }else if(playerRole.equals(RoleList.RENA_RYUGU.getRole().getName())){
                ScenarioList.DOLL.getScenario().solution(2);
            }else if(playerRole.equals(RoleList.MION_SONOZAKI.getRole().getName())){
                ScenarioList.DOLL.getScenario().solution(3);
            }else{
                ScenarioList.DOLL.getScenario().solution(4);
            }

            item.setType(Material.AIR);

        }

    }

}
