package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class MercenaireAction implements RoleAction, Listener {

    private HPlayer cible;

    @Override
    public Role getLinkedRole(){
        return Role.MERCENAIRE;
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §4Mercenaire (non-genré) : \n" +
                "\n" +
                "§6Les §4mercenaires §6doivent gagner avec leur camp. \n" +
                "§6Durant la nuit de la Watanagashi, les §4mercenaires §6gagnent l’effet force. \n" +
                "§6Si §2Tomitake §6meurt le jour de Watanagashi, les §4mercenaires §6gagneront l’effet force permanent. \n" +
                "§6Si les §4mercenaires §6remplissent leur mission d’assassinat de §4Miyo Takano §6alors ils recevront un cœur permanent supplémentaire. \n" +
                "§6Les §4mercenaires §6détiennent aléatoirement un 'kit' à l’annonce des rôles.";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if (getCible() != null && killer.getPlayer() != null) {

            if (killed == getCible()) {

                killer.getPlayer().setMaxHealth(killer.getPlayer().getMaxHealth() + 1);
                setCible(null);

            }

        }
    }

    @Override
    public void onGameStart() {
        List<HPlayer> hPlayerList = this.getLinkedRole().getHPlayerList();
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

    @EventHandler
    public void wataChange(WatanagashiChangeEvent watanagashiChangeEvent) {

        if (watanagashiChangeEvent.getWataEnum() != WataEnum.DURING)
            return;

        for (HPlayer player : Role.MERCENAIRE.getHPlayerList()) {
            if (player.getPlayer() != null)
                player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false));
        }

    }

}
