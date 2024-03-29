package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class JiroTomitakeAction implements RoleAction, Listener {

    @Override
    public Role getLinkedRole(){
        return Role.JIRO_TOMITAKE;
    }

    @Override
    public String getDescription() {
        return "§f------------------------------------------------------\n" +
                "§fVous venez d’apprendre que les §4mercenaires §fveulent votre mort !\n" +
                "\n" +
                "§6Vous êtes §2Jiro Tomitake (garçon) : \n" +
                "\n" +
                "§6Vous possédez toujours votre kit mais vous faites maintenant partie du camp §9Hinamizawa §6!\n" +
                "§f------------------------------------------------------";
    }

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent wce) {
        if (wce.getWataEnum() != WataEnum.AFTER)
            return;

        HPlayer tomitake = Role.JIRO_TOMITAKE.getHPlayer();

        if (tomitake != null && tomitake.getPlayerState() != PlayerState.SPECTATE)
            for (HPlayer hPlayer : Role.MERCENAIRE.getHPlayerList())
                if (hPlayer.getPlayer() != null)
                    hPlayer.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

    }

    @EventHandler
    public void onChangeEP(EpisodeUpdate eu) {

        if (HigurashiUHC.getGameManager().getWataState() == WataEnum.BEFORE
                && HigurashiUHC.getGameManager().getEpisode() + HigurashiUHC.getInstance().getConfig().getInt("role.tomitake.knownrole") == GameManager.getWataEpisode()
        ) {
            HPlayer jiro = null;
            for (HPlayer hPlayer : Role.MERCENAIRE.getHPlayerList())
                if (hPlayer.getInfoData().getBoolean("hiddenJiro")) {
                    jiro = hPlayer;
                    break;
                }

            if (jiro != null)
                jiro.setRole(Role.JIRO_TOMITAKE, true);
        }

    }

}
