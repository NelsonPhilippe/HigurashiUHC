package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebaraAction;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EpisodeListener implements Listener {

    private final ArrayList<Integer> avEPOyashiro = new ArrayList<Integer>() {{
        for (int ep = 3; ep <= 5; ep++)
            add(ep);
    }};

    private final Integer randomEP = avEPOyashiro.get(new Random().nextInt(avEPOyashiro.size()));

    @EventHandler
    public void onEpisodeUpdated(EpisodeUpdate e) {

        CustomCraft.baseballBat.setUsedOnEpisode(false);
        HPlayer miyoTakanoAction = Role.MIYO_TAKANO.getHPlayer();

        if (miyoTakanoAction != null)
            miyoTakanoAction.setCommandAccess(Commands.ASSASSINER, 2);

        if (HigurashiUHC.getGameManager().getEpisode() == GameManager.getWataEpisode()) {
            HigurashiUHC.getGameManager().setWataState(WataEnum.DURING);
        } else if (HigurashiUHC.getGameManager().getEpisode() == GameManager.getWataEpisode() + 1) {
            HigurashiUHC.getGameManager().setWataState(WataEnum.AFTER);
            KeiichiMaebaraAction role = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();
            HPlayer player = role.getLinkedRole().getHPlayer();
            if (player != null)
                if (player.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
                    Role shion = Role.SHION_SONOSAKI;
                    if (shion.getHPlayer() != null && shion.getHPlayer().getPlayer() != null)
                        shion.getHPlayer().getPlayer().sendMessage("Je te donne une petite info: " + player.getName() + " est marié à " + player.getMarriedPlayer(Reason.DOLL_TRAGEDY).get(0).getName());
                }

            HPlayer tomitake = Role.JIRO_TOMITAKE.getHPlayer();

            if (tomitake != null && tomitake.getPlayerState() != PlayerState.SPECTATE)
                for (HPlayer hPlayer : Role.MERCENAIRE.getHPlayerList())
                    if (hPlayer.getPlayer() != null)
                        hPlayer.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

            if (ScenarioList.OYASHIRO.isActive())
                ((Oyashiro) ScenarioList.OYASHIRO.getScenario()).revealOyashiro();

        }

        if (ScenarioList.getActiveScenario() != null) {

            if (ScenarioList.DOLL.isActive() && ScenarioList.DOLL.getScenario().getSolutionNumber() == 4) {

                HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
                if (keiichi != null && keiichi.getPlayer() != null)
                    if (keiichi.getPlayer().getMaxHealth() < 20) {
                        keiichi.getPlayer().sendMessage("DOLL) Un episode est passé, tu viens de gagner un coeur de plus.");
                        keiichi.getPlayer().setMaxHealth(keiichi.getPlayer().getMaxHealth() + 1);
                    }

            }

        } else if (randomEP == e.getEpisode()) {

            List<HPlayer> roleLists = Clans.MEMBER_OF_CLUB.getHPlayerList();

            if (!roleLists.isEmpty())
                roleLists.get(new Random().nextInt(roleLists.size())).addMaledictionReason(Reason.OYASHIRO_TRAGEDY_EPISODE);

        }

        if (HigurashiUHC.getGameManager().isWataState(WataEnum.DURING)) {

            HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();

            if (keiichi == null) return;

            Player bKeiichi = keiichi.getPlayer();
            if (bKeiichi == null) return;

            Inventory inventory = bKeiichi.getInventory();

            for (ItemStack itemStack : inventory.getContents()) {

                if (itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(DollItem.dollItem.getLore())) {

                    ScenarioList.DOLL.getScenario().solution(4);
                    itemStack.setType(Material.AIR);
                    break;

                }

            }

            for (HPlayer player : Role.MERCENAIRE.getHPlayerList()) {
                if (player.getPlayer() != null)
                    player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false));
            }

            HPlayer tomitake = Role.JIRO_TOMITAKE.getHPlayer();

            if (tomitake == null) return;

            Player bTomitake = tomitake.getPlayer();

            if (bTomitake == null) return;

            HPlayer miyo = Role.MIYO_TAKANO.getHPlayer();

            if (miyo == null) return;

            Player bMiyo = miyo.getPlayer();

            if (bMiyo == null) return;

            bMiyo.sendMessage(tomitake.getRole().getName() + " est " + bTomitake.getName());

        }

    }

}
