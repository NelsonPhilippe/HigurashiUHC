package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class JiroTomitakeAction extends RoleAction implements Listener {

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
    public void onWataChange(WatanagashiChangeEvent wce) {
        if (wce.getWataEnum() != WataEnum.AFTER)
            return;

        HPlayer tomitake = Role.JIRO_TOMITAKE.getHPlayer();

        if (tomitake != null && tomitake.getPlayerState() != PlayerState.SPECTATE)
            for (HPlayer hPlayer : Role.MERCENAIRE.getHPlayerList())
                if (hPlayer.getPlayer() != null)
                    hPlayer.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

    }

}
