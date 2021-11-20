package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class OkonogiAction extends RoleAction {

    @Override
    public String getDescription() {
        return "§6Vous êtes §4Okonogi (garçon) : \n" +
                "\n" +
                "§4Okonogi §6doit gagner avec les §4Mercenaires. \n" +
                "§4Okonogi §6connaît §4Miyo Takano. \n" +
                "§6Il a la possibilité d’ajouter les §4mercenaires §6dans une liste pour qu’ils se connaissent entre eux. \n" +
                "§6Pour se faire, il devra utiliser la commande : §5“/h liste <joueur>” §6et devra être à moins de 20 blocs du joueur pendant 30 secondes. \n" +
                "§6Les joueurs présents dans la liste pourront discuter avec un chat spécial en mettant le caractère “!” avant le message. \n" +
                "§6Si tous les §4mercenaires §6sont ajoutés dans la liste, §4Okonogi §6bénéficiera d’un cœur permanent supplémentaire (§2Tomitake §6n’est pas compris dedans). \n" +
                "§6Si §4Okonogi §6met dans la liste un joueur qui n’est pas §4mercenaire§6, alors le joueur pourra écrire, lire, et aura les pseudos brouillés dans la liste.\n" +
                "\n" +
                "§4Takano §6est §7“joueur”.";
    }

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
        HPlayer okonogi = getLinkedRole().getHPlayer();
        HPlayer miyoTakano = Role.MIYO_TAKANO.getHPlayer();

        if (okonogi != null && okonogi.getPlayer() != null && miyoTakano != null)
            okonogi.getPlayer().sendMessage("Miyo Takano est joué par: " + miyoTakano.getName());
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
