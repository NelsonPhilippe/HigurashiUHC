package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

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
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {
        List<HPlayer> hPlayerList = getLinkedRole().getHPlayerList();
        KitList[] listKit = KitList.values();

        for (HPlayer mercenaire : hPlayerList) {

            KitList kit = listKit[new Random().nextInt(listKit.length)];
            mercenaire.setKit(kit);

            String message = "Vous avez recu le kit: " + kit.name();
            if (kit == KitList.VOLEUR) {
                List<Commands> stolable = Commands.getStoleCommande();
                Commands stole = stolable.get(new Random().nextInt(stolable.size()));
                mercenaire.addCommandAccess(stole);
                message += ", vous avez recu la commande: " + stole.name();
            }

            if (mercenaire.getPlayer() != null)
                mercenaire.getPlayer().sendMessage(message);

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
