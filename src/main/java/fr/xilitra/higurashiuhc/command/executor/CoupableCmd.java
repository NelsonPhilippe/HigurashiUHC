package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoupableCmd extends CommandsExecutor {

    public CoupableCmd() {
        super("[Coupable]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] args) {

        HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(args[1]);
        if (targetHPlayer == null) {
            p.sendMessage("Cible non trouvé");
            return false;
        }

        KuraudoOishiAction oishi = (KuraudoOishiAction) hPlayer.getRole().getRoleAction();

        if (!oishi.isCoupableDesigned()) {
            oishi.setCoupableDesigned(true);
        }

        p.sendMessage("Vous avez désigné le joueur " + targetHPlayer.getName() + " coupable");

        if (targetHPlayer.getClans().isClans(Clans.SONOZAKI)) {
            Clans.POLICE.setMajorClans(null);
            ItemStack arc = new ItemStack(Material.BOW, 1);
            arc.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

            p.getInventory().addItem(arc);
        }else if (targetHPlayer.getClans().isClans(Clans.HINAMIZAWA)) {
            p.sendMessage("L'enquête a échoué.");
            return true;
        }

        if (targetHPlayer.getRole().isRole(Role.RIKA_FURUDE))
            Clans.HINAMIZAWA.addPlayer(targetHPlayer);

        p.sendMessage("Le role du joueur est : " + targetHPlayer.getRole().getDisplayName());
        p.sendMessage("Le clan du joueur est : " + targetHPlayer.getClans().getName());

        return true;

    }
}
