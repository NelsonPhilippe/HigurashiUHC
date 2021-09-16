package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EpisodeListener implements Listener {

    @EventHandler
    public void onEpisodeUpdated(EpisodeUpdate e){

        CustomCraft.baseballBat.setUsedOnEpisode(false);
        MiyoTakano miyoTakano = (MiyoTakano) RoleList.MIYO_TAKANO.getRole();
        miyoTakano.setOrder(2);

        if(HigurashiUHC.getGameManager().getEpisode() == HigurashiUHC.getInstance().getConfig().getInt("game.watanagashi")){

            HigurashiUHC.getGameManager().setWatanagashi(true);
            KeiichiMaebara role = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();
            HPlayer player = role.getHPlayer();
            if(player != null){

                if(player.hasMarriedReason(Reason.DOLL_TRAGEDY)){

                    Role shion = RoleList.SHION_SONOSAKI.getRole();
                    if(shion.getHPlayer() != null && shion.getHPlayer().getPlayer() != null)
                    shion.getHPlayer().getPlayer().sendMessage("Je te donne une petite info: "+player.getName()+" est marié à "+player.getMarriedPlayer(Reason.DOLL_TRAGEDY).get(0).getName());

                }

            }

        }

        if(ScenarioList.OYASHIRO.isActive())
            ((Oyashiro)ScenarioList.OYASHIRO.getScenario()).revealOyashiro();

        if(ScenarioList.DOLL.isActive() && ScenarioList.DOLL.getScenario().getSolutionNumber() == 4) {

            HPlayer keiichi = RoleList.KEIICHI_MAEBARA.getRole().getHPlayer();
            if (keiichi == null || keiichi.getPlayer() == null) return;
            if (keiichi.getPlayer().getMaxHealth() < 20)
                keiichi.getPlayer().setMaxHealth(keiichi.getPlayer().getMaxHealth() + 1);

        }

        if(HigurashiUHC.getGameManager().getEpisode() == 7){

            HPlayer tomitake = RoleList.JIRO_TOMITAKE.getRole().getHPlayer();

            if(tomitake == null) return;

            if(tomitake.getPlayerState() == PlayerState.SPECTATE) return;

            for(HPlayer hPlayer : RoleList.MERCENAIRE.getRole().getHPlayerList())
                if(hPlayer.getPlayer() != null)
                    hPlayer.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

        }

        if(HigurashiUHC.getGameManager().isWatanagashi()){

            HPlayer keiichi = RoleList.KEIICHI_MAEBARA.getRole().getHPlayer();

            if(keiichi == null) return;

            Player bKeiichi = keiichi.getPlayer();
            if(bKeiichi == null) return;

            Inventory inventory = bKeiichi.getInventory();

            for(ItemStack itemStack : inventory.getContents()){

                if(itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(DollItem.dollItem.getLore())){

                    ScenarioList.DOLL.getScenario().solution(4);
                    itemStack.setType(Material.AIR);

                }

            }

            HPlayer tomitake = RoleList.JIRO_TOMITAKE.getRole().getHPlayer();

            if(tomitake == null) return;

            Player bTomitake = tomitake.getPlayer();

            if(bTomitake == null) return;

            HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getHPlayer();

            if(miyo == null) return;

            Player bMiyo = miyo.getPlayer();

            if(bMiyo == null) return;

            bMiyo.sendMessage(tomitake.getRole().getName() + " est " + bTomitake.getName());

            for(HPlayer player : RoleList.MERCENAIRE.getRole().getHPlayerList()){
                if(player.getPlayer() != null)
                player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false));
            }


        }

    }

}
