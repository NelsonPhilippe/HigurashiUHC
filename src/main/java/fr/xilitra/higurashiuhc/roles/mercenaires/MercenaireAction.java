package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class MercenaireAction extends RoleAction {

    private HPlayer cible;

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {
        if (getCible() != null && getLinkedRole().getHPlayer() != null && getLinkedRole().getHPlayer().getPlayer() != null) {

            if (killed == getCible()) {

                getLinkedRole().getHPlayer().getPlayer().setMaxHealth(getLinkedRole().getHPlayer().getPlayer().getMaxHealth() + 1);
                setCible(null);

            }

        }
    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
