package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.game.task.taskClass.CouldownMatraque;
import fr.xilitra.higurashiuhc.game.task.taskClass.StuntTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class AkasakaAction implements RoleAction, Listener {

    private int countCompare = 0;

    @Override
    public Role getLinkedRole(){
        return Role.AKASAKA;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §eAkasaka (garçon) : \n" +
                "\n" +
                "§eAkasaka §6doit gagner avec §9Hinamizawa §6et fait partie du camp de la §epolice. \n" +
                "§eAkasaka §6peut enquêter sur l’identité de §9Rika §63 fois dans la partie avec la commande §5'/h "+ Commands.RIKA.getInitials() +" <joueur>'.\n" +
                " §6Si §eAkasaka §6trouve §9Rika §6a sa première enquête il gagne 3 cœurs, à sa seconde enquête 2 cœurs et à sa troisième enquête 1 cœur. \n" +
                "§6Une fois la personne déclarée coupable par §eOishi §6et s’il s’agit d’un membre des §3Sonozaki, §6si Akasaka §6trouve l’identité de §9Rika, §6alors celui-ci rejoindra le camp d’§9Hinamizawa §6et ne gagnera pas avec le camp de la §epolice. \n" +
                "§6A l’inverse, si §eAkasaka §6ne trouve pas L’identité de §9Rika, §6alors celui-ci restera dans le camp de la §epolice §6et devra gagner avec eux. \n" +
                "§6Grâce à une matraque §eAkasaka §6peut assommer un joueur pendant 3 secondes, il peut utiliser l’item une fois toutes les 5 minutes.";
    }

    @EventHandler
    public void onPlayerDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());
        if (hPlayer == null)
            return;

        ItemStack item = event.getItem();

        if (item.getItemMeta().getLore().get(0).equals(MatraqueItem.matraqueItem.getLore())) {

            if (MatraqueItem.matraqueItem.isUse()) {
                return;
            }

            hPlayer.setPlayerDontMove(true);
            new StuntTask(hPlayer).runTaskTimer(1, 1);
            new CouldownMatraque().runTaskLater(HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.ROLE_ASAKA_MATRAQUE_SECONDS));
        }

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());

        if (hPlayer == null)
            return;

        if (hPlayer.playerDontMove()) {
            event.setCancelled(true);
        }
    }

    public int getCountCompare() {
        return countCompare;
    }

    public void setCountCompare(int countCompare) {
        this.countCompare = countCompare;
    }

    @Override
    public void onGameStart() {
        HPlayer akasaka = this.getLinkedRole().getHPlayer();
        if (akasaka == null)
            return;
        if (akasaka.getPlayer() == null)
            return;
        akasaka.getPlayer().getInventory().addItem(MatraqueItem.matraqueItem.getItemStack());
    }

}
