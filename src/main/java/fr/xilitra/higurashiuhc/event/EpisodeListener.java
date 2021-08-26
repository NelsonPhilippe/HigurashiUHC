package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.scenario.Scenario;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EpisodeListener implements Listener {

    @EventHandler
    public void onEpisodeUpdated(EpisodeUpdate e){

        CustomCraft.baseballBat.setUsedOnEpisode(false);
        MiyoTakano miyoTakano = (MiyoTakano) RoleList.MIYO_TAKANO.getRole();
        miyoTakano.setOrder(2);

        if(HigurashiUHC.getGameManager().getEpisode() == 6){

            HigurashiUHC.getGameManager().setWatanagashi(true);

        }

        if(HigurashiUHC.getGameManager().isWatanagashi()){

            HPlayer keiichi = RoleList.KEIICHI_MAEBARA.getRole().getPlayer();

            if(keiichi == null) return;

            Player bKeiichi = keiichi.getPlayer();

            Inventory inventory = bKeiichi.getInventory();

            for(ItemStack itemStack : inventory.getContents()){

                if(itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(DollItem.dollItem.getLore())){

                    ScenarioList.DOLL.getScenario().solution(4);
                    itemStack.setType(Material.AIR);

                }

            }

            HPlayer tomitake = RoleList.JIRO_TOMITAKE.getRole().getPlayer();

            if(tomitake == null) return;

            Player bTomitake = tomitake.getPlayer();

            HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getPlayer();

            if(miyo == null) return;

            Player bMiyo = miyo.getPlayer();

            bMiyo.sendMessage(tomitake.getRoleList().getRole().getName() + " est " + bTomitake.getName());


        }

    }

}
